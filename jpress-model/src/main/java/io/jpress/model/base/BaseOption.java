package io.jpress.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JPress, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOption<M extends BaseOption<M>> extends JbootModel<M> implements IBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	public void setId(java.lang.Long id) {
		set("id", id);
	}

    /**
     * 主键ID
     */
	public java.lang.Long getId() {
		return getLong("id");
	}

    /**
     * 配置KEY
     */
	public void setKey(java.lang.String key) {
		set("key", key);
	}

    /**
     * 配置KEY
     */
	public java.lang.String getKey() {
		return getStr("key");
	}

    /**
     * 配置内容
     */
	public void setValue(java.lang.String value) {
		set("value", value);
	}

    /**
     * 配置内容
     */
	public java.lang.String getValue() {
		return getStr("value");
	}

    /**
     * 站点ID
     */
	public void setSiteId(java.lang.Long siteId) {
		set("site_id", siteId);
	}

    /**
     * 站点ID
     */
	public java.lang.Long getSiteId() {
		return getLong("site_id");
	}

}

