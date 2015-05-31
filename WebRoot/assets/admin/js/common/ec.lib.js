/*
 * ECommerce JS库
 */

(function( EC, $, undefined ) {
	
	"use strict"; // 使用严格模式
	
    // Default Spinner Options
    var opts = {
		lines : 12, // The number of lines to draw
		length : 8, // The length of each line
		width : 2, // The line thickness
		radius : 5, // The radius of the inner circle
		corners : 1, // Corner roundness (0..1)
		rotate : 0, // The rotation offset
		direction : 1, // 1: clockwise, -1: counterclockwise
		color : '#000', // #rgb or #rrggbb or array of colors
		speed : 1.0, // Rounds per second
		trail : 60, // Afterglow percentage
		shadow : true, // Whether to render a shadow
		hwaccel : false, // Whether to use hardware acceleration
		className : 'spinner', // The CSS class to assign to the spinner
		zIndex : 2e9, // The z-index (defaults to 2000000000)
		top : 'auto', // Top position relative to parent in px
		left : 'auto', // Left position relative to parent in px
    };
    var spinner = {};
    
    // 创建Spinner
    EC.createSpinner = function(spinDomId) {
    	if (EC.isEmptyObj(spinner)) { spinner = new Spinner(opts); } // 重复利用对象
		spinDomId = spinDomId || "defaultSpin";
		if ($("#" + spinDomId).length == 0) {
			// 默认在网页中间加载
			$('<div id=' + spinDomId + ' style="position:fixed;top:50%;left:50%"></div>').appendTo('body');
		}
		
		var target = document.getElementById(spinDomId);
		spinner.spin(target);
    };
    
    // 删除Spinner
    EC.deleteSpinner = function() {
    	if (EC.isEmptyObj(spinner)) { return ; }
    	spinner.spin();
    };

    // 添加遮罩层
    EC.appendBackdrop = function() {
    };
    
    // 删除遮罩层
    EC.deleteBackdrop = function() {
    };
    
    /**
     * 添加加载层
     * 
     * @param {String} backdrop 是否添加遮罩层
     * @param {String} modalId 自定义的模态框ID
     */
    EC.showLoadingLayer = function(backdrop, modalId) {
    	modalId = modalId || 'modal-loading'; 
    	backdrop = backdrop || 'static'; // 是否添加遮罩层
    	if ($("#modal-loading").length > 0) {
    		$("#modal-loading").modal({backdrop: backdrop, keyboard: false});
    		return ;
		}
    	
    	var loadingMoadl =
    	'<div class="modal fade" tabindex="-1" id="' + modalId + '">'
	    	+ '<div class="modal-dialog">'
	    		+ '<div class="modal-content">'
	    			+ '<div class="modal-body">'
	    				+ '<div class="progress progress-small progress-striped active">'
	    					+ '<div style="width: 100%;" class="progress-bar"></div>'
	    				+ '</div>'
	    				+ '<div class="bootbox-body" style="text-align:center">处理中...</div>'
	    			+ '</div>'
	    		+ '</div>'
	    	+ '</div>'
	    + '</div>';
    	
    	$(loadingMoadl).appendTo("body");
    	$("#modal-loading").modal({backdrop: backdrop, keyboard: false});
    };
    
    // 删除加载层
    EC.delLoadingLayer = function(modalId) {
    	modalId = modalId || 'modal-loading';
    	$("#" + modalId).modal('hide');
    };
    
    // 修改加载进度
    EC.changeLoadingProgress = function(progress, modalId) {
    	modalId = modalId || 'modal-loading';
    	$("#" + modalId).find(".progress-bar").css('width', progress + "%");
    };
    
    // 清空表单数据
    EC.clearFormData = function(form) {
    	// iterate over all of the inputs for the form
    	// element that was passed in
    	$(':input', form).each(function() {
    		var type = this.type;
    		var tag = this.tagName.toLowerCase(); // normalize case
    		// it's ok to reset the value attr of text inputs,
    		// password inputs, and textareas
    		if (type == 'text' || type == 'password' || tag == 'textarea')
    			this.value = "";
    			// checkboxes and radios need to have their checked state cleared
    			// but should *not* have their 'value' changed
    		else if (type == 'checkbox' || type == 'radio')
    			this.checked = false;
    			// select elements need to have their 'selectedIndex' property set to -1
    			// (this works for both single and multiple select elements)
    		else if (tag == 'select')
    			this.selectedIndex = -1;
    	});
    };
    
    // 创建Noty通知
    EC.createNoty = function(messageType, messageText) {
    	messageType = messageType || "success"; // 默认提示类型是 成功
    	messageText = messageText || "操作成功"; // 默认提示信息是 操作成功
    	
    	if ("error" == messageType) {
    		EC.deleteSpinner(); // 错误提示都删除动态加载插件
    	}
    	var n = noty({
    		theme: 'defaultTheme', 
    		type: messageType, 
    		layout: 'topCenter', 
    		text: messageText, 
    		timeout: 1000, // delay for closing event. Set false for sticky notifications
    		// modal: true
    	});
    };
    
    // 判断对象属性是否为空（对象已存在）
    EC.isEmptyObj = function(obj) {
    	for (var property in obj) { return false; }
    	return true;
    };
    
    /**
     * 是否JSON字符串
     * 
     * @param {String} str 字符串
     */
    EC.isJsonString = function(str) {
    	if(str.match("^{(.+:.+,*){1,}}$")) {
    		return true;
    	}
    	return false;
    };
    
    /**
     * 过滤HTML标签
     * 
     * @param {String} content 字符内容
     */
    EC.htmlTagFilter = function(str) {
    	str = str.replace(/<\/?[^>]*>/g, ''); // 去除HTML tag
        str = str.replace(/[ | ]*\n/g, '\n'); // 去除行尾空白
        str = str.replace(/\n[\s| | ]*\r/g, '\n'); // 去除多余空行
        str = str.replace(/&nbsp;/ig, ''); // 去掉&nbsp;
        return str;
    };
    
    /**
     * 是否图片
     * 
     * @param {Object} fileInput input对象（file）
     */
    EC.isPicture = function(fileInput) {
    	var $fileInput = $(fileInput);
    	var file = $fileInput.val();
		if(!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(file)) {
			return false;
		}
		return true;
    };
    
    /**
     * 图片大小（支持HTML5的浏览器）
     * 
     * @param {Object} fileInput input对象（file）
     * @return {Number} 图片大小（单位：kb）
     */
    EC.getImgSizeHtml5 = function(fileInput) {
    	var size = fileInput[0].files[0].size;
    	
    	return size;
    };
    
    // 浏览器是否支持HTML5
    EC.supportHtml5 = function() {
    	if (typeof(Worker) == "undefined") {   
            return false;
        }
    	return true;
    };
    
    /**
     * 浏览器是否支持某属性
     * 
     * @param {String} featurename 属性
     */
    EC.detectCSSFeature = function(featurename) {
    	var feature = false,
        domPrefixes = 'Webkit Moz ms O'.split(' '),
        elm = document.createElement('div'),
        featurenameCapital = null;

        featurename = featurename.toLowerCase();

        if( elm.style[featurename] ) { feature = true; } 

        if( feature === false ) {
            featurenameCapital = featurename.charAt(0).toUpperCase() + featurename.substr(1);
            for( var i = 0; i < domPrefixes.length; i++ ) {
                if( elm.style[domPrefixes[i] + featurenameCapital ] !== undefined ) {
                  feature = true;
                  break;
                }
            }
        }
        return feature;
    };
    
    /**
     * 判断是否函数对象（使用jQuery提供的工具类）
     * 
     * @param {Object} object 对象
     * @see jQuery.js
     */
    EC.isFunction = function(object) {
    	return $.isFunction(object); 
    };
    
    /**
     * 拼接数组参数，比如{'a', 'b'}拼接成'a, b'
     * 
     * @param {Array} array 参数数组
     * @param {String} splitter 分隔符
     */
    EC.concatStr = function(array, splitter) {
    	var str = "";
    	splitter = splitter || ',';
    	for ( var i = 0; i < array.length; i++) {
			if (i == (array.length - 1)) {
				str += array[i];
			} else {
				str = str + array[i] + splitter;
			}
		}
    	
    	return str;
    };
    
    /**       
     * 对Date的扩展，将 Date 转化为指定格式的String       
     * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
     * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
     * eg:       
     * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
     * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
     * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
     * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
     * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
     * 
     * @param date {Date} JS时间对象
     * @param format {String} 转化的时间格式
     */
    EC.formatData = function(date, format) {
    	var o = {           
		    "M+" : date.getMonth()+1, //月份 
		    "d+" : date.getDate(), //日 
		    "h+" : date.getHours()%12 == 0 ? 12 : date.getHours()%12, //小时  
		    "H+" : date.getHours(), //小时 
		    "m+" : date.getMinutes(), //分 
		    "s+" : date.getSeconds(), //秒 
		    "q+" : Math.floor((date.getMonth()+3)/3), //季度 
		    "S" : date.getMilliseconds() //毫秒 
	    };           
	    var week = {           
		    "0" : "/u65e5",
		    "1" : "/u4e00",
		    "2" : "/u4e8c",
		    "3" : "/u4e09",
		    "4" : "/u56db",
		    "5" : "/u4e94",           
		    "6" : "/u516d"
	    };           
	    if(/(y+)/.test(format)) { 
	        format = format.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));           
	    }
	    if(/(E+)/.test(format)) {
	        format = format.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[date.getDay()+""]);           
	    }
	    for(var k in o) {
	        if(new RegExp("("+ k +")").test(format)) {
	            format = format.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
	        }
	    }
	    
	    return format;
    };
    
    // 获取上下文路径
    EC.getContextPath = function() {
		var contextPath = document.location.pathname;
	    var index = contextPath.substr(1).indexOf("/");
	    contextPath = contextPath.substr(0, (index + 1));
	    // delete index; // applying the 'delete' operator to an unqualified name is deprecated
	    index = undefined; // the garbage collector will free the data
	    return contextPath;
	};

}( window.EC = window.EC || {}, jQuery ));
