/*
 * ECommerce通用JS文件
 */

"use strict"; // 使用严格模式

var crud = {
	operatedObj: { 
	}, 
	addInit: function() {
		EC.createSpinner();
		var url = EC.getContextPath() + $("#ajaxPathPrefix").val() + "/addEntrance";
		// 动态加载
		$("#addModal").load(url, function(responseText, status, req) {
			if (!crud.initCallBack("addModal", responseText)) {return ;} 
			$("#modal-add").modal({
				backdrop : 'static'
			});
			
			EC.deleteSpinner();
		});
		
	}, 
	readInit: function(itemId) {
		EC.createSpinner(); // 创建JS加载插件
		var url = EC.getContextPath() + $("#ajaxPathPrefix").val() + "/readEntrance";
		// 动态加载
		$("#readModal").load(url, {"id": itemId}, function(responseText, status, req) {
			if (!crud.initCallBack("readModal", responseText)) {return ;} 
			// 显示查看Modal
  			$("#modal-read").modal({
		  		backdrop: 'static'
			});
			
			EC.deleteSpinner();
		});
	}, 
	updateInit: function(itemId) {
		EC.createSpinner(); // 创建JS加载插件
		var url = EC.getContextPath() + $("#ajaxPathPrefix").val() + "/updateEntrance";
		// 动态加载
		$("#editModal").load(url, {"id": itemId}, function(responseText, status, req) {
			if (!crud.initCallBack("editModal", responseText, status)) {return ;} 
			$("#modal-edit").modal({
		 		backdrop: 'static'
			});
			
			EC.deleteSpinner(); // 删除JS加载插件
		});
	},
	deleteInit: function(itemId) {
		EC.createSpinner(); // 创建JS加载插件
		var url = EC.getContextPath() + $("#ajaxPathPrefix").val() + "/deleteEntrance";
		// 动态加载
		$("#deleteModal").load(url, function(responseText, status, req) {
			if (!crud.initCallBack("deleteModal", responseText)) {return ;} 
			$("#modal-delete").modal({
		 		backdrop: 'static'
			});
			
			EC.deleteSpinner(); // 删除JS加载插件
		});
	}, 
	batchDeleteInit: function(operatedObj) {
		var url = EC.getContextPath() + $("#ajaxPathPrefix").val() + "/batchDelete";
		alert(operatedObj);
	}, 
	initCallBack: function(modalId, responseText) {
		// 初始化函数的回调处理
		// 加载页面失败
		if ("error" == status) {
			EC.createNoty("error", "页面加载失败！");
			return false;
		}
		// 如果responseText是JSON格式
		if (EC.isJsonString(responseText)) {
			responseText = eval ("(" + responseText + ")");
			
			var result = handleAjaxResults(responseText);
			if (result) {
			
			} else {
				$("#" + modalId).html(""); // 清空Modal内容
			}
			return result;
		}
		return true;
	}
};

// 增删改查，批量删除
$(function() {
	$(".btn-add, .btn-read, .btn-edit, .btn-delete, .btn-batchDelete").click(function() {
		crud.operatedObj = $(this);
		if (crud.operatedObj.hasClass("btn-add")) {
			crud.addInit();
		} else if (crud.operatedObj.hasClass("btn-read")) {
			crud.readInit(crud.operatedObj.val());
		} else if (crud.operatedObj.hasClass("btn-edit")) {
			crud.updateInit(crud.operatedObj.val());
		} else if (crud.operatedObj.hasClass("btn-delete")) {
			crud.deleteInit(crud.operatedObj.val());
		} else if (crud.operatedObj.hasClass("btn-batchDelete")) {
			crud.batchDeleteInit(crud.operatedObj);
		}
	});
});
/*--------------------------------------------------------------*/

/*----------------------选择zTree树形列表操作-------------------------*/
/**
 * 选择zTree树形列表时执行的操作，通用于整个项目
 * 
 * @param zTreeObj zTree对象
 * @param selectedNumsObj 显示选中项数量span的jQuery对象
 * @param selectedTipsSObj 显示提示信息span的jQuery对象
 * @param buttonObj 批量操作button的jQuery对象
 */
function selectZTree(zTreeObj, selectedNumsObj, selectedTipsSObj, buttonObj) {
	var selectedNums = 0;	// 被选中项的数量
	
	// 计算选中节点的数量
	selectedNums = zTreeObj.getCheckedNodes(true).length; 
	if (selectedNums > 0) {
		selectedNumsObj.text(selectedNums);	// 输出被选中项数量
		if (buttonObj.attr("disabled")) {
			// 存在disabled属性则移除
			buttonObj.removeAttr("disabled");
		}
		
		if (selectedTipsSObj.attr("style")) {
			// 存在style属性则移除
			selectedTipsSObj.attr("style", "");
		}
	} else {
		buttonObj.attr("disabled", "disabled");
		selectedTipsSObj.css("display", "none");
	}
	
	return selectedNums;
}
/*----------------------------------------------------------------*/

/*----------------------------------------------------------------*/
/**
 * 处理ajax请求成功后返回的数据
 * 
 * @param ajaxResponse ajax结果
 */
function handleAjaxResults(ajaxResponse) {
	var message = ajaxResponse.value;
	if ("success" == ajaxResponse.resultCode) {
		EC.createNoty("success", message);
		return true;
	} else if ("notLogin" == ajaxResponse.resultCode) {
		EC.createNoty("error", message);
	} else if ("noPermission" == ajaxResponse.resultCode) {
		EC.createNoty("error", message);
	} else if ("undefinedError" == ajaxResponse.resultCode) {
		EC.createNoty("error", message);
	} else {
		return true;
	}
	
	return false;
}

/*-----------------------------zTree-----------------------------*/
var zTree = {
	zTreeObj: { // zTree树对象
	}, 
	searchNodeList: { // 节点搜索数据
	}, 
	setting: { // zTree默认配置
		check: { /** 单选框 **/
			enable: true,
			chkStyle: "radio",
			autoCheckTrigger: true,
			radioType: "all"
		},  
		view: { 
			expandSpeed: 300, // 设置树展开的动画速度，IE6下面没效果，  
			showIcon: true, 
			fontCss: function(treeId, treeNode) {
				return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
			}
		},                     
		data: { 
			simpleData: {   // 简单的数据源
				enable: true,  
				idKey: "id",  //id和pid，这里不用多说了吧，树的目录级别  
				pIdKey: "pId", 
				rootPId: 0   //根节点  
			} 
	 	}, 
	 	callback: {
	 		onCheck: function(event, treeId, treeNode) {
	 			zTree.zTreeOnCheck(event, treeId, treeNode);
	 		}
	 	}
	}, 
	zTreeOnCheck: function(event, treeId, treeNode) { // checkbox / radio 被勾选 或 取消勾选的事件处理
		return ; // 默认不处理
	}, 
	zTreeFilter: function(zTreeId, key, value) { // zTree搜索过滤
		zTree.updateNodes(false); // 去掉节点高亮
		if(value != ""){  
			zTree.zTreeObj =  $.fn.zTree.getZTreeObj(zTreeId);
	        zTree.searchNodeList = zTree.zTreeObj.getNodesByParamFuzzy(key, value); 
	        // 有搜索结果
	        if (zTree.searchNodeList && zTree.searchNodeList.length > 0) {  
	            zTree.updateNodes(true); // 添加节点高亮
	        } else {
	        	zTree.zTreeObj.expandAll(false); // 无搜索结果，则折叠全部节点
	        }
		}
	}, 
	updateNodes: function(highlight) { // 更新节点
		for( var i = 0; i < zTree.searchNodeList.length;  i++) { 
			zTree.searchNodeList[i].highlight = highlight; 
	        zTree.zTreeObj.updateNode(zTree.searchNodeList[i]);
	        var childrenNodes = zTree.searchNodeList[i].children;
	        var parentNode = zTree.searchNodeList[i].getParentNode();
	        if (childrenNodes && childrenNodes.length > 0) { // 有子节点
	        	zTree.zTreeObj.expandNode(zTree.searchNodeList[i], highlight, true, false); //
			} else if (parentNode) { // 无子节点但有父节点
				zTree.zTreeObj.expandNode(parentNode, highlight, true, false); //
			}
	    }
	}, 
	getFontCss: function(treeId, treeNode) { // 节点高亮
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
};

/*----------------------------------------------------------------*/

/*--------------------------表格全局操作----------------------------*/

$(function() {
	// 全选和反选
	$('table th input:checkbox').on('click', function(){
		var that = this;
		$(this).closest('table').find('tr > td:first-child input:checkbox')
		.each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
			
	});
	
	// 批量操作
	$(".ace").on('click', function() {
		var closestTable = $(this).closest('.table-responsive');
		if (closestTable.find("input[type='checkbox'][class='ace item']:checked").length > 0) {
			closestTable.find(".batch").each(function() {
				if ($(this).attr("disabled")) {
					$(this).removeAttr("disabled");
				}
			});
		} else {
			closestTable.find(".batch").each(function() {
				if (!$(this).attr("disabled")) {
					$(this).attr("disabled", true);
				}
			});
		}
	});
});

// 添加新行
function addNewTr()	{
	// TODO 表格数据怎么传递比较合适?
}
