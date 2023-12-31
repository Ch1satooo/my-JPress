package io.jpress.module.page.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JPress, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSinglePageCategory<M extends BaseSinglePageCategory<M>> extends JbootModel<M> implements IBean {

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
     * 标题
     */
	public void setTitle(java.lang.String title) {
		set("title", title);
	}

    /**
     * 标题
     */
	public java.lang.String getTitle() {
		return getStr("title");
	}

    /**
     * 描述
     */
	public void setContent(java.lang.String content) {
		set("content", content);
	}

    /**
     * 描述
     */
	public java.lang.String getContent() {
		return getStr("content");
	}

    /**
     * 页面默认情况下使用的模板样式
     */
	public void setStyle(java.lang.String style) {
		set("style", style);
	}

    /**
     * 页面默认情况下使用的模板样式
     */
	public java.lang.String getStyle() {
		return getStr("style");
	}

    /**
     * 分类 icon
     */
	public void setIcon(java.lang.String icon) {
		set("icon", icon);
	}

    /**
     * 分类 icon
     */
	public java.lang.String getIcon() {
		return getStr("icon");
	}

    /**
     * 装饰图
     */
	public void setOrnament(java.lang.String ornament) {
		set("ornament", ornament);
	}

    /**
     * 装饰图
     */
	public java.lang.String getOrnament() {
		return getStr("ornament");
	}

    /**
     * 该分类的内容数量
     */
	public void setCount(java.lang.Long count) {
		set("count", count);
	}

    /**
     * 该分类的内容数量
     */
	public java.lang.Long getCount() {
		return getLong("count");
	}

    /**
     * 排序编码
     */
	public void setOrderNumber(java.lang.Integer orderNumber) {
		set("order_number", orderNumber);
	}

    /**
     * 排序编码
     */
	public java.lang.Integer getOrderNumber() {
		return getInt("order_number");
	}

    /**
     * 标识
     */
	public void setFlag(java.lang.String flag) {
		set("flag", flag);
	}

    /**
     * 标识
     */
	public java.lang.String getFlag() {
		return getStr("flag");
	}

    /**
     * 创建日期
     */
	public void setCreated(java.util.Date created) {
		set("created", created);
	}

    /**
     * 创建日期
     */
	public java.util.Date getCreated() {
		return getDate("created");
	}

    /**
     * 修改日期
     */
	public void setModified(java.util.Date modified) {
		set("modified", modified);
	}

    /**
     * 修改日期
     */
	public java.util.Date getModified() {
		return getDate("modified");
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

