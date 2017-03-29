/*
 * Copyright (c) 2007, 2011, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.sun.tools.visualvm.threaddump.impl;

import com.sun.tools.visualvm.application.Application;
import com.sun.tools.visualvm.core.datasource.DataSourceRepository;
import com.sun.tools.visualvm.core.datasupport.DataChangeEvent;
import com.sun.tools.visualvm.core.datasupport.DataChangeListener;
import com.sun.tools.visualvm.core.datasource.Storage;
import com.sun.tools.visualvm.core.snapshot.Snapshot;
import com.sun.tools.visualvm.threaddump.ThreadDumpSupport;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Jiri Sedlacek
 * @author Tomas Hurka
 */
public class ThreadDumpProvider {
    
    public void initialize() {
        DataSourceRepository.sharedInstance().addDataChangeListener(new SnapshotListener(), Snapshot.class);
        DataSourceRepository.sharedInstance().addDataChangeListener(new ApplicationListener(), Application.class);
    }

    private void processNewSnapshot(Snapshot snapshot) {
        if (snapshot instanceof ThreadDumpImpl) return;
        File snapshotFile = snapshot.getFile();
        if (snapshotFile != null && snapshotFile.isDirectory()) {
            File[] files = snapshotFile.listFiles(ThreadDumpSupport.getInstance().getCategory().getFilenameFilter());
            if (files == null) return;
            Set<ThreadDumpImpl> threadDumps = new HashSet();
            for (File file : files) threadDumps.add(new ThreadDumpImpl(file, snapshot));
            snapshot.getRepository().addDataSources(threadDumps);
        }
    }
    
    private void processNewApplication(Application application) {
        Storage storage = application.getStorage();
        if (storage.directoryExists()) {
            File[] files = storage.getDirectory().listFiles(ThreadDumpSupport.getInstance().getCategory().getFilenameFilter());
            if (files == null) return;
            Set<ThreadDumpImpl> threadDumps = new HashSet();
            for (File file : files) threadDumps.add(new ThreadDumpImpl(file, application));
            application.getRepository().addDataSources(threadDumps);
        }
    }
    
    private class SnapshotListener implements DataChangeListener<Snapshot> {
        
        public void dataChanged(DataChangeEvent<Snapshot> event) {
            final Set<Snapshot> snapshots = event.getAdded();
            if (!snapshots.isEmpty()) RequestProcessor.getDefault().post(new Runnable() {
                public void run() {
                    for (Snapshot snapshot : snapshots) processNewSnapshot(snapshot);
                }
            });
        }
    }
    
    private class ApplicationListener implements DataChangeListener<Application> {
        
        public void dataChanged(DataChangeEvent<Application> event) {
            final Set<Application> applications = event.getAdded();
            if (!applications.isEmpty()) RequestProcessor.getDefault().post(new Runnable() {
                public void run() {
                    for (Application application : applications) processNewApplication(application);
                }
            });
        }
    }
}