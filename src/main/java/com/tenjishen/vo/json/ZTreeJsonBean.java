package com.tenjishen.vo.json;

/**
 * 供ZTree插件初始化的JSON格式Bean
 * 
 * @author TENJI
 *
 */
public class ZTreeJsonBean {
	private Long id; // 节点ID
	private Long pId; // 父节点ID，
	private String name; // 节点名称
	private String icon; // 图标地址
	private boolean checked; // 此节点是否选中
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean getChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
