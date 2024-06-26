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
// A comment

package org.apache.roller;

import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * Base Roller exception class.
 */
public abstract class RollerException extends Exception {

    private final Throwable mRootCause;


    /**
     * Construct emtpy exception object.
     */
    protected RollerException() {
        super();
        mRootCause = null;
    }


    /**
     * Construct RollerException with message string.
     * @param s Error message string.
     */
    protected RollerException(String s) {
        super(s);
        mRootCause = null;
    }


    /**
     * Construct RollerException, wrapping existing throwable.
     * @param s Error message
     * @param t Existing connection to wrap.
     */
    protected RollerException(String s, Throwable t) {
        super(s);
        mRootCause = t;
    }


    /**
     * Construct RollerException, wrapping existing throwable.
     * @param t Existing exception to be wrapped.
     */
    protected RollerException(Throwable t) {
        mRootCause = t;
    }


    /**
     * Get root cause object, or null if none.
     * @return Root cause or null if none.
     */
    public Throwable getRootCause() {
        return mRootCause;
    }


    /**
     * Get root cause message.
     * @return Root cause message.
     */
    public String getRootCauseMessage() {
        String rcmessage = null;
        if (getRootCause()!=null) {
            if (getRootCause().getCause()!=null) {
                rcmessage = getRootCause().getCause().getMessage();
            }
            rcmessage = (rcmessage == null) ? getRootCause().getMessage() : rcmessage;
            rcmessage = (rcmessage == null) ? super.getMessage() : rcmessage;
            rcmessage = (rcmessage == null) ? "NONE" : rcmessage;
        }
        return rcmessage;
    }


    /**
     * Print stack trace for exception and for root cause exception if there is one.
     * @see java.lang.Throwable#printStackTrace()
     */
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        if (mRootCause != null) {
            System.out.println("--- ROOT CAUSE ---");
            mRootCause.printStackTrace();
        }
    }


    /**
     * Print stack trace for exception and for root cause exception if there is one.
     * @param s Stream to print to.
     */
    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        if (mRootCause != null) {
            s.println("--- ROOT CAUSE ---");
            mRootCause.printStackTrace(s);
        }
    }


    /**
     * Print stack trace for exception and for root cause exception if there is one.
     * @param s Writer to write to.
     */
    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
        if (null != mRootCause) {
            s.println("--- ROOT CAUSE ---");
            mRootCause.printStackTrace(s);
        }
    }

}
