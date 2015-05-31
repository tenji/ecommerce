/*
 * EC分页通用JS文件
 */

(function( ec_pagination, $, undefined ) {
	
	"use strict"; // 使用严格模式
	
	$(function() {
		$("#pageSize").change(function() {
			changePageSize(this);
		});
	});
	
	// 修改PageSize
	function changePageSize(select) {
		var $select = $(select);
		var href = location.href;
		var searchUrl = location.search; // 获取url中"?"符后的字串
		var pageSize = $select.val();
		if (searchUrl.length > 0) {
			if (searchUrl.indexOf("pageSize") == -1) {
				window.location.href = href + "&pageSize=" + pageSize;
			} else {
				var regExp = /pageSize=[0-9]*/;
				href = href.replace(regExp, "pageSize=" + pageSize);
				window.location.href = href;
			}
		} else {
			window.location.href = href + "?pageSize=" + pageSize;
		}
	}
	
}( window.ec_pagination = window.ec_pagination || {}, jQuery ));
