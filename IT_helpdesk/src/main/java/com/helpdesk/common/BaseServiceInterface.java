package com.helpdesk.common;


public interface BaseServiceInterface<T, Q, Z> {

    /**
     * 新增
     * Add
     * @param entity 新增实体
     * @return 操作行数
     */
    int save(T entity);

    /**
     * 修改
     * Update
     * @param entity 修改实体
     * @return 操作行数
     */
    int update(T entity);

    /**
     * 删除
     * Delete
     * @param id 主键
     * @return 操作行数
     */
    int remove(Z id);

    /**
     * 主键查询
     * Query
     * @param id 主键
     * @return 查询结果
     */
    T getById(Z id);





}

