package com.tenjishen.common.util.oscache;

import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.EntryRefreshPolicy;

public 	class EntryRefreshPolicyImpl implements EntryRefreshPolicy {
	private static final long serialVersionUID = 1L;
	private int second;

	public EntryRefreshPolicyImpl(int second) {
		this.second = second;
	}

	@Override
	public boolean needsRefresh(CacheEntry arg0) {
		Long now = System.currentTimeMillis();
		Long time = arg0.getLastUpdate() + second * 1000;
		return now > time;
	}
}
