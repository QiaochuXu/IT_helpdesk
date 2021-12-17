package com.helpdesk.common;


public interface BaseServiceInterface<T, Q, Z> {

    /**
     *
     * Add
     * @param entity
     * @return
     */
    int save(T entity);

    /**
     *
     * Update
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     *
     * Delete
     * @param id
     * @return
     */
    int remove(Z id);

    /**
     *
     * Query
     * @param id
     * @return
     */
    T getById(Z id);





}

