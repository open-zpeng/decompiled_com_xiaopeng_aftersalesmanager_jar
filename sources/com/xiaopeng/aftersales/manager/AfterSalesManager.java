package com.xiaopeng.aftersales.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.util.Preconditions;
import com.xiaopeng.aftersales.manager.IAfterSalesManager;
import com.xiaopeng.aftersales.manager.IAlertListener;
import com.xiaopeng.aftersales.manager.IAuthModeListener;
import com.xiaopeng.aftersales.manager.IDiagnosisStatusListener;
import com.xiaopeng.aftersales.manager.IEncryptShListener;
import com.xiaopeng.aftersales.manager.ILogicActionListener;
import com.xiaopeng.aftersales.manager.ILogicTreeUpgrader;
import com.xiaopeng.aftersales.manager.IRepairModeListener;
import com.xiaopeng.aftersales.manager.IShellCmdListener;

/* loaded from: classes.dex */
public class AfterSalesManager {
    private static final long AFTERSALES_SERVICE_BIND_MAX_RETRY = 20;
    private static final long AFTERSALES_SERVICE_BIND_RETRY_INTERVAL_MS = 500;
    private static final String AFTERSALES_SERVICE_CLASS = "com.xiaopeng.aftersales.service.AfterSalesService";
    public static final String AFTERSALES_SERVICE_INTERFACE_NAME = "com.xiaopeng.aftersales.manager.IAfterSalesManager";
    public static final String AFTERSALES_SERVICE_NOT_CONNECTED_EXCEPTION_MSG = "AfterSalesServiceNotConnected";
    private static final String AFTERSALES_SERVICE_PACKAGE = "com.xiaopeng.aftersales";
    public static final int CMD_TYPE_CAT = 2;
    public static final int CMD_TYPE_COMMON = 0;
    public static final int CMD_TYPE_DF = 3;
    public static final int CMD_TYPE_DU = 5;
    public static final int CMD_TYPE_GETPROP = 1;
    public static final int CMD_TYPE_IFCONFIG = 9;
    public static final int CMD_TYPE_LS_AL = 8;
    public static final int CMD_TYPE_MOUNT = 4;
    public static final int CMD_TYPE_TOP_CPU = 6;
    public static final int CMD_TYPE_TOP_MEM = 7;
    public static final int ERROR_CODE_MODULE_4G = 10;
    public static final int ERROR_CODE_MODULE_AUDIO = 1;
    public static final int ERROR_CODE_MODULE_BLUETOOTH = 3;
    public static final int ERROR_CODE_MODULE_CAMERA = 2;
    public static final int ERROR_CODE_MODULE_CARSERVICE = 14;
    public static final int ERROR_CODE_MODULE_ICM_AUDIO = 17;
    public static final int ERROR_CODE_MODULE_ICM_ETH = 18;
    public static final int ERROR_CODE_MODULE_ICM_LCD = 16;
    public static final int ERROR_CODE_MODULE_ICM_SYSTEM = 19;
    public static final int ERROR_CODE_MODULE_LCD = 8;
    public static final int ERROR_CODE_MODULE_LIBHTTP = 6;
    public static final int ERROR_CODE_MODULE_NAVI = 9;
    public static final int ERROR_CODE_MODULE_PHY = 15;
    public static final int ERROR_CODE_MODULE_PM = 12;
    public static final int ERROR_CODE_MODULE_PSO = 5;
    public static final int ERROR_CODE_MODULE_SOC = 7;
    public static final int ERROR_CODE_MODULE_UFS = 11;
    public static final int ERROR_CODE_MODULE_USB = 13;
    public static final int ERROR_CODE_MODULE_WIFI = 4;
    public static final int MAX_ERROR_CODE_MODULE = 19;
    public static final int RESULT_FAIL = 0;
    public static final int RESULT_KEEP = 2;
    public static final int RESULT_PASS = 1;
    public static final int RESULT_UNKNOWN = -1;
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_DISCONNECTED = 0;
    private static final String TAG = "AfterSalesManager";
    @GuardedBy({"this"})
    private IAfterSalesManager mAfterSalesService;
    @GuardedBy({"this"})
    private int mConnectionRetryCount;
    @GuardedBy({"this"})
    private int mConnectionState;
    private Context mContext;
    private final Handler mEventHandler;
    private final Handler mMainThreadEventHandler;
    private final ServiceConnection mServiceConnectionListenerClient;
    private final ArrayMap<AlertListener, IAlertListener> mAlertListeners = new ArrayMap<>();
    private final ArrayMap<RepairModeListener, IRepairModeListener> mRepairModeListeners = new ArrayMap<>();
    private final ArrayMap<ShellCmdListener, IShellCmdListener> mShellCmdListeners = new ArrayMap<>();
    private final ArrayMap<EncryptShListener, IEncryptShListener> mEncryptShListeners = new ArrayMap<>();
    private final ArrayMap<AuthModeListener, IAuthModeListener> mAuthModeListeners = new ArrayMap<>();
    private final ArrayMap<LogicActionListener, ILogicActionListener> mLogicActionListeners = new ArrayMap<>();
    private final ArrayMap<LogicTreeUpgrader, ILogicTreeUpgrader> mLogicTreeUpgraders = new ArrayMap<>();
    private final ArrayMap<DiagnosisStatusListener, IDiagnosisStatusListener> mDiagnosisStatusListeners = new ArrayMap<>();
    private final ServiceConnection mServiceConnectionListener = new ServiceConnection() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            synchronized (AfterSalesManager.this) {
                AfterSalesManager.this.mAfterSalesService = IAfterSalesManager.Stub.asInterface(service);
                AfterSalesManager.this.mConnectionState = 2;
            }
            AfterSalesManager.this.registerAllListener();
            if (AfterSalesManager.this.mServiceConnectionListenerClient != null) {
                AfterSalesManager.this.mServiceConnectionListenerClient.onServiceConnected(name, service);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            synchronized (AfterSalesManager.this) {
                AfterSalesManager.this.mAfterSalesService = null;
                if (AfterSalesManager.this.mConnectionState == 0) {
                    return;
                }
                AfterSalesManager.this.mConnectionState = 0;
                if (AfterSalesManager.this.mServiceConnectionListenerClient != null) {
                    AfterSalesManager.this.mServiceConnectionListenerClient.onServiceDisconnected(name);
                }
                if (!AfterSalesManager.this.mDisconnectWanted) {
                    AfterSalesManager.this.lambda$new$0$AfterSalesManager();
                }
            }
        }
    };
    private final Runnable mConnectionRetryFailedRunnable = new Runnable() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.2
        @Override // java.lang.Runnable
        public void run() {
            AfterSalesManager.this.mServiceConnectionListener.onServiceDisconnected(new ComponentName(AfterSalesManager.AFTERSALES_SERVICE_PACKAGE, AfterSalesManager.AFTERSALES_SERVICE_CLASS));
        }
    };
    private final Runnable mConnectionRetryRunnable = new Runnable() { // from class: com.xiaopeng.aftersales.manager.-$$Lambda$AfterSalesManager$qn1YtpeGUIZtH55tU90j7_12go0
        @Override // java.lang.Runnable
        public final void run() {
            AfterSalesManager.this.lambda$new$0$AfterSalesManager();
        }
    };
    private boolean mDisconnectWanted = false;

    public AfterSalesManager(Context context, ServiceConnection serviceConnectionListener, Handler handler) {
        this.mContext = context;
        this.mServiceConnectionListenerClient = serviceConnectionListener;
        this.mEventHandler = determineEventHandler(handler);
        this.mMainThreadEventHandler = determineMainThreadEventHandler(this.mEventHandler);
    }

    public static AfterSalesManager createAfterSalesManager(Context context, ServiceConnection serviceConnectionListener, Handler handler) {
        try {
            return new AfterSalesManager(context, serviceConnectionListener, handler);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static AfterSalesManager createAfterSalesManager(Context context, ServiceConnection serviceConnectionListener) {
        return createAfterSalesManager(context, serviceConnectionListener, null);
    }

    public static AfterSalesManager createAfterSalesManager(Context context) {
        return createAfterSalesManager(context, null, null);
    }

    private static Handler determineMainThreadEventHandler(Handler eventHandler) {
        Looper mainLooper = Looper.getMainLooper();
        return eventHandler.getLooper() == mainLooper ? eventHandler : new Handler(mainLooper);
    }

    private static Handler determineEventHandler(Handler handler) {
        if (handler == null) {
            Looper looper = Looper.getMainLooper();
            return new Handler(looper);
        }
        return handler;
    }

    public static void checkAfterSalesServiceNotConnectedException(IllegalStateException e) throws AfterSalesServiceNotConnectedException, IllegalStateException {
        String message = e.getMessage();
        if (message.equals(AFTERSALES_SERVICE_NOT_CONNECTED_EXCEPTION_MSG)) {
            throw new AfterSalesServiceNotConnectedException();
        }
        throw e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerAllListener() {
        Log.d(TAG, "registerAllListener.");
        for (AlertListener l : this.mAlertListeners.keySet()) {
            try {
                this.mAfterSalesService.registerAlertListener(this.mAlertListeners.get(l));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        for (LogicActionListener l2 : this.mLogicActionListeners.keySet()) {
            try {
                this.mAfterSalesService.registerLogicActionListener(this.mLogicActionListeners.get(l2));
            } catch (RemoteException e2) {
                throw e2.rethrowFromSystemServer();
            }
        }
        for (RepairModeListener l3 : this.mRepairModeListeners.keySet()) {
            try {
                this.mAfterSalesService.registerRepairModeListener(this.mRepairModeListeners.get(l3));
            } catch (RemoteException e3) {
                throw e3.rethrowFromSystemServer();
            }
        }
        for (AuthModeListener l4 : this.mAuthModeListeners.keySet()) {
            try {
                this.mAfterSalesService.registerAuthModeListener(this.mAuthModeListeners.get(l4));
            } catch (RemoteException e4) {
                throw e4.rethrowFromSystemServer();
            }
        }
        for (ShellCmdListener l5 : this.mShellCmdListeners.keySet()) {
            try {
                this.mAfterSalesService.registerShellCmdListener(this.mShellCmdListeners.get(l5));
            } catch (RemoteException e5) {
                throw e5.rethrowFromSystemServer();
            }
        }
        for (EncryptShListener l6 : this.mEncryptShListeners.keySet()) {
            try {
                this.mAfterSalesService.registerEncryptShListener(this.mEncryptShListeners.get(l6));
            } catch (RemoteException e6) {
                throw e6.rethrowFromSystemServer();
            }
        }
        for (DiagnosisStatusListener l7 : this.mDiagnosisStatusListeners.keySet()) {
            try {
                this.mAfterSalesService.registerDiagnosisStatusListener(this.mDiagnosisStatusListeners.get(l7));
            } catch (RemoteException e7) {
                throw e7.rethrowFromSystemServer();
            }
        }
    }

    private void unregisterAllListener() {
        Log.d(TAG, "unregisterAllListener.");
        if (isConnected()) {
            for (AlertListener l : this.mAlertListeners.keySet()) {
                try {
                    this.mAfterSalesService.unregisterAlertListener(this.mAlertListeners.get(l));
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            for (LogicActionListener l2 : this.mLogicActionListeners.keySet()) {
                try {
                    this.mAfterSalesService.unregisterLogicActionListener(this.mLogicActionListeners.get(l2));
                } catch (RemoteException e2) {
                    throw e2.rethrowFromSystemServer();
                }
            }
            for (RepairModeListener l3 : this.mRepairModeListeners.keySet()) {
                try {
                    this.mAfterSalesService.unregisterRepairModeListener(this.mRepairModeListeners.get(l3));
                } catch (RemoteException e3) {
                    throw e3.rethrowFromSystemServer();
                }
            }
            for (AuthModeListener l4 : this.mAuthModeListeners.keySet()) {
                try {
                    this.mAfterSalesService.unregisterAuthModeListener(this.mAuthModeListeners.get(l4));
                } catch (RemoteException e4) {
                    throw e4.rethrowFromSystemServer();
                }
            }
            for (ShellCmdListener l5 : this.mShellCmdListeners.keySet()) {
                try {
                    this.mAfterSalesService.unregisterShellCmdListener(this.mShellCmdListeners.get(l5));
                } catch (RemoteException e5) {
                    throw e5.rethrowFromSystemServer();
                }
            }
            for (EncryptShListener l6 : this.mEncryptShListeners.keySet()) {
                try {
                    this.mAfterSalesService.unregisterEncryptShListener(this.mEncryptShListeners.get(l6));
                } catch (RemoteException e6) {
                    throw e6.rethrowFromSystemServer();
                }
            }
            for (DiagnosisStatusListener l7 : this.mDiagnosisStatusListeners.keySet()) {
                try {
                    this.mAfterSalesService.unregisterDiagnosisStatusListener(this.mDiagnosisStatusListeners.get(l7));
                } catch (RemoteException e7) {
                    throw e7.rethrowFromSystemServer();
                }
            }
        }
        this.mAlertListeners.clear();
        this.mLogicActionListeners.clear();
        this.mRepairModeListeners.clear();
        this.mAuthModeListeners.clear();
        this.mShellCmdListeners.clear();
        this.mEncryptShListeners.clear();
        this.mDiagnosisStatusListeners.clear();
    }

    public void connect() throws IllegalStateException {
        Log.d(TAG, "connect.");
        synchronized (this) {
            if (this.mConnectionState != 0) {
                throw new IllegalStateException("already connected or connecting");
            }
            this.mConnectionState = 1;
            this.mDisconnectWanted = false;
            lambda$new$0$AfterSalesManager();
        }
    }

    public void disconnect() {
        Log.d(TAG, "disconnect.");
        synchronized (this) {
            this.mDisconnectWanted = true;
            unregisterAllListener();
            if (this.mConnectionState == 0) {
                return;
            }
            this.mEventHandler.removeCallbacks(this.mConnectionRetryRunnable);
            this.mMainThreadEventHandler.removeCallbacks(this.mConnectionRetryFailedRunnable);
            this.mConnectionRetryCount = 0;
            this.mAfterSalesService = null;
            this.mConnectionState = 0;
            this.mContext.unbindService(this.mServiceConnectionListener);
        }
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this) {
            z = this.mAfterSalesService != null;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this) {
            z = true;
            if (this.mConnectionState != 1) {
                z = false;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startAfterSalesService */
    public void lambda$new$0$AfterSalesManager() {
        Intent intent = new Intent();
        intent.setPackage(AFTERSALES_SERVICE_PACKAGE);
        intent.setAction(AFTERSALES_SERVICE_INTERFACE_NAME);
        boolean bound = this.mContext.bindServiceAsUser(intent, this.mServiceConnectionListener, 1, UserHandle.CURRENT_OR_SELF);
        if (!bound) {
            this.mConnectionRetryCount++;
            if (this.mConnectionRetryCount > AFTERSALES_SERVICE_BIND_MAX_RETRY) {
                Log.w(TAG, "cannot bind to AfterSalesService after max retry");
                this.mMainThreadEventHandler.post(this.mConnectionRetryFailedRunnable);
                return;
            }
            this.mEventHandler.postDelayed(this.mConnectionRetryRunnable, AFTERSALES_SERVICE_BIND_RETRY_INTERVAL_MS);
            return;
        }
        this.mConnectionRetryCount = 0;
    }

    public void addAlertListener(final AlertListener l) {
        IAlertListener rl = new IAlertListener.Stub() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.3
            @Override // com.xiaopeng.aftersales.manager.IAlertListener
            public void alertDiagnosisError(int module, int errorCode, long time, String errorMsg) throws RemoteException {
                l.alertDiagnosisError(module, errorCode, time, errorMsg);
            }
        };
        this.mAlertListeners.put(l, rl);
        if (isConnected()) {
            try {
                this.mAfterSalesService.registerAlertListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void removeAlertListener(AlertListener l) {
        IAlertListener rl = this.mAlertListeners.remove(l);
        Preconditions.checkArgument(rl != null, "AlertListener was not registered.");
        if (isConnected()) {
            try {
                this.mAfterSalesService.unregisterAlertListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void recordDiagnosisError(int module, int errorCode, long millis, String errorMsg, boolean alert) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.recordDiagnosisError(module, errorCode, millis, errorMsg, alert);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void updateDiagnosisUploadStatus(int module, boolean result, int errorCode, long millis, String errorMsg) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.updateDiagnosisUploadStatus(module, result, errorCode, millis, errorMsg);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addDiagnosisStatusListener(final DiagnosisStatusListener l) {
        IDiagnosisStatusListener rl = new IDiagnosisStatusListener.Stub() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.4
            @Override // com.xiaopeng.aftersales.manager.IDiagnosisStatusListener
            public void onDiagnosisStatusChanged(int module, int errorCode, long time, String errorMsg, int status) throws RemoteException {
                l.onDiagnosisStatusChanged(module, errorCode, time, errorMsg, status);
            }
        };
        this.mDiagnosisStatusListeners.put(l, rl);
        if (isConnected()) {
            try {
                this.mAfterSalesService.registerDiagnosisStatusListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void removeDiagnosisStatusListener(DiagnosisStatusListener l) {
        IDiagnosisStatusListener rl = this.mDiagnosisStatusListeners.remove(l);
        Preconditions.checkArgument(rl != null, "DiagnosisStatusListener was not registered.");
        if (isConnected()) {
            try {
                this.mAfterSalesService.unregisterDiagnosisStatusListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void addLogicActionListener(final LogicActionListener l) {
        ILogicActionListener rl = new ILogicActionListener.Stub() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.5
            @Override // com.xiaopeng.aftersales.manager.ILogicActionListener
            public void uploadLogicAction(String issueName, String conclusion, String startTime, String endTime, String logicactionTime, String logicactionEntry, String logictreeVer) throws RemoteException {
                l.uploadLogicAction(issueName, conclusion, startTime, endTime, logicactionTime, logicactionEntry, logictreeVer);
            }
        };
        this.mLogicActionListeners.put(l, rl);
        if (isConnected()) {
            try {
                this.mAfterSalesService.registerLogicActionListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void removeLogicActionListener(LogicActionListener l) {
        ILogicActionListener rl = this.mLogicActionListeners.remove(l);
        Preconditions.checkArgument(rl != null, "LogicActionListener was not registered.");
        if (isConnected()) {
            try {
                this.mAfterSalesService.unregisterLogicActionListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void recordLogicAction(String issueName, String conclusion, String startTime, String endTime, String logicactionTime, String logicactionEntry, String logictreeVer) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.recordLogicAction(issueName, conclusion, startTime, endTime, logicactionTime, logicactionEntry, logictreeVer);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void updateLogicActionUploadStatus(boolean status, String issueName, String conclusion, String startTime, String endTime, String logicactionTime, String logicactionEntry, String logictreeVer) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.updateLogicActionUploadStatus(status, issueName, conclusion, startTime, endTime, logicactionTime, logicactionEntry, logictreeVer);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void requestUploadLogicAction() {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.requestUploadLogicAction();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void requestUpgradeLogicTree(String path) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.requestUpgradeLogicTree(path);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addLogicTreeUpgrader(final LogicTreeUpgrader l) {
        ILogicTreeUpgrader rl = new ILogicTreeUpgrader.Stub() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.6
            @Override // com.xiaopeng.aftersales.manager.ILogicTreeUpgrader
            public void onUpgradeStatus(boolean status) throws RemoteException {
                l.onUpgradeStatus(status);
            }
        };
        this.mLogicTreeUpgraders.put(l, rl);
        if (isConnected()) {
            try {
                this.mAfterSalesService.registerLogicTreeUpgrader(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void removeLogicTreeUpgrader(LogicTreeUpgrader l) {
        ILogicTreeUpgrader rl = this.mLogicTreeUpgraders.get(l);
        Preconditions.checkArgument(rl != null, "LogicTreeUpgrader was not registered.");
        if (isConnected()) {
            try {
                this.mAfterSalesService.unregisterLogicTreeUpgrader(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void recordRepairModeAction(String action, String result) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.recordRepairmodeAction(action, result);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void enableRepairMode() {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.enableRepairMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void enableRepairModeWithKey(String keyPath) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.enableRepairModeWithKey(keyPath);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void enableRepairModeWithKeyId(String keyId) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.enableRepairModeWithKeyId(keyId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void disableRepairMode() {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.disableRepairMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean getRepairMode() {
        if (isConnected()) {
            try {
                boolean repairmode = this.mAfterSalesService.getRepairMode();
                return repairmode;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return false;
    }

    public String getRepairModeEnableTime() {
        if (isConnected()) {
            try {
                String repairmodeEnableTime = this.mAfterSalesService.getRepairModeEnableTime();
                return repairmodeEnableTime;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return null;
    }

    public String getRepairModeDisableTime() {
        if (isConnected()) {
            try {
                String repairmodeDisableTime = this.mAfterSalesService.getRepairModeDisableTime();
                return repairmodeDisableTime;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return null;
    }

    public boolean getSpeedLimitMode() {
        if (isConnected()) {
            try {
                boolean speedLimitMode = this.mAfterSalesService.getSpeedLimitMode();
                return speedLimitMode;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return false;
    }

    public String getSpeedLimitEnableTime() {
        if (isConnected()) {
            try {
                String speedLimitEnableTime = this.mAfterSalesService.getSpeedLimitEnableTime();
                return speedLimitEnableTime;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return null;
    }

    public String getSpeedLimitDisableTime() {
        if (isConnected()) {
            try {
                String speedLimitDisableTime = this.mAfterSalesService.getSpeedLimitDisableTime();
                return speedLimitDisableTime;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return null;
    }

    public String getRepairModeKeyId() {
        if (isConnected()) {
            try {
                String repairModeKeyId = this.mAfterSalesService.getRepairModeKeyId();
                return repairModeKeyId;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return null;
    }

    public void recordSpeedLimitOn() {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.recordSpeedLimitOn();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void recordSpeedLimitOff() {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.recordSpeedLimitOff();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void registerRepairModeListener(final RepairModeListener l) {
        IRepairModeListener rl = new IRepairModeListener.Stub() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.7
            @Override // com.xiaopeng.aftersales.manager.IRepairModeListener
            public void onRepairModeChanged(boolean onoff, int switchResult) throws RemoteException {
                l.onRepairModeChanged(onoff, switchResult);
            }
        };
        this.mRepairModeListeners.put(l, rl);
        if (isConnected()) {
            try {
                this.mAfterSalesService.registerRepairModeListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void unregisterRepairModeListener(RepairModeListener l) {
        IRepairModeListener rl = this.mRepairModeListeners.remove(l);
        Preconditions.checkArgument(rl != null, "RepairModeListener was not registered.");
        if (isConnected()) {
            try {
                this.mAfterSalesService.unregisterRepairModeListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void executeShellCmd(int cmdtype, String param, boolean isCloudCmd) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.executeShellCmd(cmdtype, param, isCloudCmd);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void executeShellCmdWithLimitLine(int cmdtype, String param, int limitLine, String quitcmd, boolean isCloudCmd) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.executeShellCmdWithLimitLine(cmdtype, param, limitLine, quitcmd, isCloudCmd);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void registerShellCmdListener(final ShellCmdListener l) {
        IShellCmdListener rl = new IShellCmdListener.Stub() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.8
            @Override // com.xiaopeng.aftersales.manager.IShellCmdListener
            public void onShellResponse(int errorcode, String resultPath, boolean isCloudCmd) throws RemoteException {
                l.onShellResponse(errorcode, resultPath, isCloudCmd);
            }
        };
        this.mShellCmdListeners.put(l, rl);
        if (isConnected()) {
            try {
                this.mAfterSalesService.registerShellCmdListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void unregisterShellCmdListener(ShellCmdListener l) {
        IShellCmdListener rl = this.mShellCmdListeners.remove(l);
        Preconditions.checkArgument(rl != null, "ShellCmdListener was not registered.");
        if (isConnected()) {
            try {
                this.mAfterSalesService.unregisterShellCmdListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void executeEncryptSh(String path, boolean isCloudCmd) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.executeEncryptSh(path, isCloudCmd);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void registerEncryptShListener(final EncryptShListener l) {
        IEncryptShListener rl = new IEncryptShListener.Stub() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.9
            @Override // com.xiaopeng.aftersales.manager.IEncryptShListener
            public void onEncryptShResponse(int errorcode, String resultPath, String outputPath, boolean isCloudCmd) throws RemoteException {
                l.onEncryptShResponse(errorcode, resultPath, outputPath, isCloudCmd);
            }
        };
        this.mEncryptShListeners.put(l, rl);
        if (isConnected()) {
            try {
                this.mAfterSalesService.registerEncryptShListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void unregisterEncryptShListener(EncryptShListener l) {
        IEncryptShListener rl = this.mEncryptShListeners.remove(l);
        Preconditions.checkArgument(rl != null, "EncryptShListener was not registered.");
        if (isConnected()) {
            try {
                this.mAfterSalesService.unregisterEncryptShListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void enableAuthMode(String value, long time) {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.enableAuthMode(value, time);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void disableAuthMode() {
        if (!isConnected()) {
            Log.e(TAG, "AfterSalesService is disconnected.");
            return;
        }
        try {
            this.mAfterSalesService.disableAuthMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean getAuthMode() {
        if (isConnected()) {
            try {
                boolean authmode = this.mAfterSalesService.getAuthMode();
                return authmode;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return false;
    }

    public String getAuthPass() {
        if (isConnected()) {
            try {
                String authPass = this.mAfterSalesService.getAuthPass();
                return authPass;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return null;
    }

    public long getAuthEndTime() {
        if (isConnected()) {
            try {
                long authPass = this.mAfterSalesService.getAuthEndTime();
                return authPass;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
        return -1L;
    }

    public void registerAuthModeListener(final AuthModeListener l) {
        IAuthModeListener rl = new IAuthModeListener.Stub() { // from class: com.xiaopeng.aftersales.manager.AfterSalesManager.10
            @Override // com.xiaopeng.aftersales.manager.IAuthModeListener
            public void onAuthModeChanged(boolean onoff, int switchResult) throws RemoteException {
                l.onAuthModeChanged(onoff, switchResult);
            }
        };
        this.mAuthModeListeners.put(l, rl);
        if (isConnected()) {
            try {
                this.mAfterSalesService.registerAuthModeListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }

    public void unregisterAuthModeListener(AuthModeListener l) {
        IAuthModeListener rl = this.mAuthModeListeners.remove(l);
        Preconditions.checkArgument(rl != null, "AuthModeListener was not registered.");
        if (isConnected()) {
            try {
                this.mAfterSalesService.unregisterAuthModeListener(rl);
                return;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        Log.e(TAG, "AfterSalesService is disconnected.");
    }
}
