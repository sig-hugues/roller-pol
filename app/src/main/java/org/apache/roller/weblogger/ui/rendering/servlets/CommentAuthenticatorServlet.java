/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */

package org.apache.roller.weblogger.ui.rendering.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.roller.weblogger.config.WebloggerConfig;
import org.apache.roller.weblogger.ui.rendering.plugins.comments.CommentAuthenticator;
import org.apache.roller.weblogger.ui.rendering.plugins.comments.DefaultCommentAuthenticator;
import org.apache.roller.weblogger.util.Reflection;


/**
 * The CommentAuthenticatorServlet is used for generating the html used for
 * comment authentication.  This is done outside of the normal rendering process
 * so that we can cache full pages and still set the comment authentication
 * section dynamically.
 */
public class CommentAuthenticatorServlet extends HttpServlet {
    
    private static final Log mLogger = LogFactory.getLog(CommentAuthenticatorServlet.class);
    
    private CommentAuthenticator authenticator = null;
    
    
    /**
     * Handle incoming http GET requests.
     *
     * We only handle get requests.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        response.setContentType("text/html; charset=utf-8");

        // Convince proxies and IE not to cache this.
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:00 GMT");

        PrintWriter out = response.getWriter();
        //out.println(this.authenticator.getHtml(request));
    }
    
    
    /** 
     * Initialization.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        
        super.init(config);
        
        // lookup the authenticator we are going to use and instantiate it
        try {
            String name = WebloggerConfig.getProperty("comment.authenticator.classname");
            this.authenticator = (CommentAuthenticator) Reflection.newInstance(name);
            
        } catch(ReflectiveOperationException e) {
            mLogger.error(e);
        } finally {
            if(authenticator == null) {
                this.authenticator = new DefaultCommentAuthenticator();
            }
        }
        

    }
    
    /** 
     * Destruction.
     */
    @Override
    public void destroy() {}
    
}
