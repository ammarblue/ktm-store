/*
 * $Id: Globals.java 471754 2006-11-06 14:55:09Z husted $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ktm.utils;

import java.io.Serializable;

public class Globals implements Serializable {

    private static final long  serialVersionUID                   = -2065449278377178400L;

    public static final String FORWARD_QUERY_STRING               = "javax.servlet.forward.query_string";
    public static final String FORWARD_REQUEST_URI                = "javax.servlet.forward.request_uri";
    public static final String FORWARD_SERVLET_PATH               = "javax.servlet.forward.servlet_path";
    public static final String FORWARD_CONTEXT_PATH               = "javax.servlet.forward.context_path";
    public static final String MAIN_PAGE                          = "MainPage";
    public static final String LOGIN_PAGE                         = "jspLoginPage";
    public static final String BASE_PATH                          = "BasePath";
    public static final String ENTITY_SESSION                     = "entity_session";
    public static final String ENTITY_SESSION_END_OF_CONVERSATION = "entity_session_end_of_conversion";
    public static final Object ANY                                = Integer.valueOf(0);

    // javax.servlet.include.request_uri
    // javax.servlet.include.context_path
    // javax.servlet.include.servlet_path
    // javax.servlet.include.path_info
    // javax.servlet.include.query_string
}
