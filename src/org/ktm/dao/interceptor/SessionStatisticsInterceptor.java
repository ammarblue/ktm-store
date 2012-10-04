package org.ktm.dao.interceptor;

import org.hibernate.Interceptor;
import org.ktm.utils.CrudAdmin;

public interface SessionStatisticsInterceptor extends Interceptor {

    public CrudAdmin getCrudAdmin();
}
