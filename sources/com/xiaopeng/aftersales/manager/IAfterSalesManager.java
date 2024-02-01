package com.xiaopeng.aftersales.manager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.aftersales.manager.IAlertListener;
import com.xiaopeng.aftersales.manager.IAuthModeListener;
import com.xiaopeng.aftersales.manager.IDiagnosisStatusListener;
import com.xiaopeng.aftersales.manager.IEncryptShListener;
import com.xiaopeng.aftersales.manager.ILogicActionListener;
import com.xiaopeng.aftersales.manager.ILogicTreeUpgrader;
import com.xiaopeng.aftersales.manager.IRepairModeListener;
import com.xiaopeng.aftersales.manager.IShellCmdListener;

/* loaded from: classes.dex */
public interface IAfterSalesManager extends IInterface {
    void disableAuthMode() throws RemoteException;

    void disableRepairMode() throws RemoteException;

    void enableAuthMode(String str, long j) throws RemoteException;

    void enableRepairMode() throws RemoteException;

    void enableRepairModeWithKey(String str) throws RemoteException;

    void enableRepairModeWithKeyId(String str) throws RemoteException;

    void executeEncryptSh(String str, boolean z) throws RemoteException;

    void executeShellCmd(int i, String str, boolean z) throws RemoteException;

    void executeShellCmdWithLimitLine(int i, String str, int i2, String str2, boolean z) throws RemoteException;

    long getAuthEndTime() throws RemoteException;

    boolean getAuthMode() throws RemoteException;

    String getAuthPass() throws RemoteException;

    boolean getRepairMode() throws RemoteException;

    String getRepairModeDisableTime() throws RemoteException;

    String getRepairModeEnableTime() throws RemoteException;

    String getRepairModeKeyId() throws RemoteException;

    String getSpeedLimitDisableTime() throws RemoteException;

    String getSpeedLimitEnableTime() throws RemoteException;

    boolean getSpeedLimitMode() throws RemoteException;

    void recordDiagnosisError(int i, int i2, long j, String str, boolean z) throws RemoteException;

    void recordLogicAction(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws RemoteException;

    void recordRepairmodeAction(String str, String str2) throws RemoteException;

    void recordSpeedLimitOff() throws RemoteException;

    void recordSpeedLimitOn() throws RemoteException;

    void registerAlertListener(IAlertListener iAlertListener) throws RemoteException;

    void registerAuthModeListener(IAuthModeListener iAuthModeListener) throws RemoteException;

    void registerDiagnosisStatusListener(IDiagnosisStatusListener iDiagnosisStatusListener) throws RemoteException;

    void registerEncryptShListener(IEncryptShListener iEncryptShListener) throws RemoteException;

    void registerLogicActionListener(ILogicActionListener iLogicActionListener) throws RemoteException;

    void registerLogicTreeUpgrader(ILogicTreeUpgrader iLogicTreeUpgrader) throws RemoteException;

    void registerRepairModeListener(IRepairModeListener iRepairModeListener) throws RemoteException;

    void registerShellCmdListener(IShellCmdListener iShellCmdListener) throws RemoteException;

    void requestUpgradeLogicTree(String str) throws RemoteException;

    void requestUploadLogicAction() throws RemoteException;

    void unregisterAlertListener(IAlertListener iAlertListener) throws RemoteException;

    void unregisterAuthModeListener(IAuthModeListener iAuthModeListener) throws RemoteException;

    void unregisterDiagnosisStatusListener(IDiagnosisStatusListener iDiagnosisStatusListener) throws RemoteException;

    void unregisterEncryptShListener(IEncryptShListener iEncryptShListener) throws RemoteException;

    void unregisterLogicActionListener(ILogicActionListener iLogicActionListener) throws RemoteException;

    void unregisterLogicTreeUpgrader(ILogicTreeUpgrader iLogicTreeUpgrader) throws RemoteException;

    void unregisterRepairModeListener(IRepairModeListener iRepairModeListener) throws RemoteException;

    void unregisterShellCmdListener(IShellCmdListener iShellCmdListener) throws RemoteException;

    void updateDiagnosisUploadStatus(int i, boolean z, int i2, long j, String str) throws RemoteException;

    void updateLogicActionUploadStatus(boolean z, String str, String str2, String str3, String str4, String str5, String str6, String str7) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IAfterSalesManager {
        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void recordDiagnosisError(int module, int errorCode, long millis, String errorMsg, boolean alert) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void updateDiagnosisUploadStatus(int module, boolean status, int errorCode, long millis, String errorMsg) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void registerAlertListener(IAlertListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void unregisterAlertListener(IAlertListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void registerDiagnosisStatusListener(IDiagnosisStatusListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void unregisterDiagnosisStatusListener(IDiagnosisStatusListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void recordLogicAction(String issueName, String conclusion, String startTime, String endTime, String logicactionTime, String logicactionEntry, String logictreeVer) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void updateLogicActionUploadStatus(boolean status, String issueName, String conclusion, String startTime, String endTime, String logicactionTime, String logicactionEntry, String logictreeVer) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void requestUploadLogicAction() throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void requestUpgradeLogicTree(String path) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void registerLogicActionListener(ILogicActionListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void unregisterLogicActionListener(ILogicActionListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void registerLogicTreeUpgrader(ILogicTreeUpgrader listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void unregisterLogicTreeUpgrader(ILogicTreeUpgrader listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void enableRepairMode() throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void enableRepairModeWithKey(String keyPath) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void enableRepairModeWithKeyId(String keyId) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void disableRepairMode() throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public boolean getRepairMode() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public String getRepairModeEnableTime() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public String getRepairModeDisableTime() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public boolean getSpeedLimitMode() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public String getSpeedLimitEnableTime() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public String getSpeedLimitDisableTime() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public String getRepairModeKeyId() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void recordSpeedLimitOn() throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void recordSpeedLimitOff() throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void registerRepairModeListener(IRepairModeListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void unregisterRepairModeListener(IRepairModeListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void recordRepairmodeAction(String action, String result) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void executeShellCmd(int cmdtype, String param, boolean isCloudCmd) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void executeShellCmdWithLimitLine(int cmdtype, String param, int limitLine, String quitcmd, boolean isCloudCmd) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void registerShellCmdListener(IShellCmdListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void unregisterShellCmdListener(IShellCmdListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void executeEncryptSh(String path, boolean isCloudCmd) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void registerEncryptShListener(IEncryptShListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void unregisterEncryptShListener(IEncryptShListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void enableAuthMode(String value, long time) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void disableAuthMode() throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public boolean getAuthMode() throws RemoteException {
            return false;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public String getAuthPass() throws RemoteException {
            return null;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public long getAuthEndTime() throws RemoteException {
            return 0L;
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void registerAuthModeListener(IAuthModeListener listener) throws RemoteException {
        }

        @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
        public void unregisterAuthModeListener(IAuthModeListener listener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IAfterSalesManager {
        private static final String DESCRIPTOR = "com.xiaopeng.aftersales.manager.IAfterSalesManager";
        static final int TRANSACTION_disableAuthMode = 39;
        static final int TRANSACTION_disableRepairMode = 18;
        static final int TRANSACTION_enableAuthMode = 38;
        static final int TRANSACTION_enableRepairMode = 15;
        static final int TRANSACTION_enableRepairModeWithKey = 16;
        static final int TRANSACTION_enableRepairModeWithKeyId = 17;
        static final int TRANSACTION_executeEncryptSh = 35;
        static final int TRANSACTION_executeShellCmd = 31;
        static final int TRANSACTION_executeShellCmdWithLimitLine = 32;
        static final int TRANSACTION_getAuthEndTime = 42;
        static final int TRANSACTION_getAuthMode = 40;
        static final int TRANSACTION_getAuthPass = 41;
        static final int TRANSACTION_getRepairMode = 19;
        static final int TRANSACTION_getRepairModeDisableTime = 21;
        static final int TRANSACTION_getRepairModeEnableTime = 20;
        static final int TRANSACTION_getRepairModeKeyId = 25;
        static final int TRANSACTION_getSpeedLimitDisableTime = 24;
        static final int TRANSACTION_getSpeedLimitEnableTime = 23;
        static final int TRANSACTION_getSpeedLimitMode = 22;
        static final int TRANSACTION_recordDiagnosisError = 1;
        static final int TRANSACTION_recordLogicAction = 7;
        static final int TRANSACTION_recordRepairmodeAction = 30;
        static final int TRANSACTION_recordSpeedLimitOff = 27;
        static final int TRANSACTION_recordSpeedLimitOn = 26;
        static final int TRANSACTION_registerAlertListener = 3;
        static final int TRANSACTION_registerAuthModeListener = 43;
        static final int TRANSACTION_registerDiagnosisStatusListener = 5;
        static final int TRANSACTION_registerEncryptShListener = 36;
        static final int TRANSACTION_registerLogicActionListener = 11;
        static final int TRANSACTION_registerLogicTreeUpgrader = 13;
        static final int TRANSACTION_registerRepairModeListener = 28;
        static final int TRANSACTION_registerShellCmdListener = 33;
        static final int TRANSACTION_requestUpgradeLogicTree = 10;
        static final int TRANSACTION_requestUploadLogicAction = 9;
        static final int TRANSACTION_unregisterAlertListener = 4;
        static final int TRANSACTION_unregisterAuthModeListener = 44;
        static final int TRANSACTION_unregisterDiagnosisStatusListener = 6;
        static final int TRANSACTION_unregisterEncryptShListener = 37;
        static final int TRANSACTION_unregisterLogicActionListener = 12;
        static final int TRANSACTION_unregisterLogicTreeUpgrader = 14;
        static final int TRANSACTION_unregisterRepairModeListener = 29;
        static final int TRANSACTION_unregisterShellCmdListener = 34;
        static final int TRANSACTION_updateDiagnosisUploadStatus = 2;
        static final int TRANSACTION_updateLogicActionUploadStatus = 8;

        public Stub() {
            attachInterface(this, "com.xiaopeng.aftersales.manager.IAfterSalesManager");
        }

        public static IAfterSalesManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
            if (iin != null && (iin instanceof IAfterSalesManager)) {
                return (IAfterSalesManager) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg1;
            if (code == 1598968902) {
                reply.writeString("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    int _arg0 = data.readInt();
                    int _arg12 = data.readInt();
                    long _arg2 = data.readLong();
                    String _arg3 = data.readString();
                    boolean _arg4 = data.readInt() != 0;
                    recordDiagnosisError(_arg0, _arg12, _arg2, _arg3, _arg4);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    int _arg02 = data.readInt();
                    boolean _arg13 = data.readInt() != 0;
                    int _arg22 = data.readInt();
                    long _arg32 = data.readLong();
                    String _arg42 = data.readString();
                    updateDiagnosisUploadStatus(_arg02, _arg13, _arg22, _arg32, _arg42);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IAlertListener _arg03 = IAlertListener.Stub.asInterface(data.readStrongBinder());
                    registerAlertListener(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IAlertListener _arg04 = IAlertListener.Stub.asInterface(data.readStrongBinder());
                    unregisterAlertListener(_arg04);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IDiagnosisStatusListener _arg05 = IDiagnosisStatusListener.Stub.asInterface(data.readStrongBinder());
                    registerDiagnosisStatusListener(_arg05);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IDiagnosisStatusListener _arg06 = IDiagnosisStatusListener.Stub.asInterface(data.readStrongBinder());
                    unregisterDiagnosisStatusListener(_arg06);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _arg07 = data.readString();
                    String _arg14 = data.readString();
                    String _arg23 = data.readString();
                    String _arg33 = data.readString();
                    String _arg43 = data.readString();
                    String _arg5 = data.readString();
                    String _arg6 = data.readString();
                    recordLogicAction(_arg07, _arg14, _arg23, _arg33, _arg43, _arg5, _arg6);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _arg08 = data.readInt() != 0;
                    String _arg15 = data.readString();
                    String _arg24 = data.readString();
                    String _arg34 = data.readString();
                    String _arg44 = data.readString();
                    String _arg52 = data.readString();
                    String _arg62 = data.readString();
                    String _arg7 = data.readString();
                    updateLogicActionUploadStatus(_arg08, _arg15, _arg24, _arg34, _arg44, _arg52, _arg62, _arg7);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    requestUploadLogicAction();
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _arg09 = data.readString();
                    requestUpgradeLogicTree(_arg09);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    ILogicActionListener _arg010 = ILogicActionListener.Stub.asInterface(data.readStrongBinder());
                    registerLogicActionListener(_arg010);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    ILogicActionListener _arg011 = ILogicActionListener.Stub.asInterface(data.readStrongBinder());
                    unregisterLogicActionListener(_arg011);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    ILogicTreeUpgrader _arg012 = ILogicTreeUpgrader.Stub.asInterface(data.readStrongBinder());
                    registerLogicTreeUpgrader(_arg012);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    ILogicTreeUpgrader _arg013 = ILogicTreeUpgrader.Stub.asInterface(data.readStrongBinder());
                    unregisterLogicTreeUpgrader(_arg013);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    enableRepairMode();
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _arg014 = data.readString();
                    enableRepairModeWithKey(_arg014);
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _arg015 = data.readString();
                    enableRepairModeWithKeyId(_arg015);
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    disableRepairMode();
                    reply.writeNoException();
                    return true;
                case 19:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean repairMode = getRepairMode();
                    reply.writeNoException();
                    reply.writeInt(repairMode ? 1 : 0);
                    return true;
                case TRANSACTION_getRepairModeEnableTime /* 20 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _result = getRepairModeEnableTime();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_getRepairModeDisableTime /* 21 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _result2 = getRepairModeDisableTime();
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case TRANSACTION_getSpeedLimitMode /* 22 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean speedLimitMode = getSpeedLimitMode();
                    reply.writeNoException();
                    reply.writeInt(speedLimitMode ? 1 : 0);
                    return true;
                case TRANSACTION_getSpeedLimitEnableTime /* 23 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _result3 = getSpeedLimitEnableTime();
                    reply.writeNoException();
                    reply.writeString(_result3);
                    return true;
                case TRANSACTION_getSpeedLimitDisableTime /* 24 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _result4 = getSpeedLimitDisableTime();
                    reply.writeNoException();
                    reply.writeString(_result4);
                    return true;
                case TRANSACTION_getRepairModeKeyId /* 25 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _result5 = getRepairModeKeyId();
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case TRANSACTION_recordSpeedLimitOn /* 26 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    recordSpeedLimitOn();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_recordSpeedLimitOff /* 27 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    recordSpeedLimitOff();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_registerRepairModeListener /* 28 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IRepairModeListener _arg016 = IRepairModeListener.Stub.asInterface(data.readStrongBinder());
                    registerRepairModeListener(_arg016);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_unregisterRepairModeListener /* 29 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IRepairModeListener _arg017 = IRepairModeListener.Stub.asInterface(data.readStrongBinder());
                    unregisterRepairModeListener(_arg017);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_recordRepairmodeAction /* 30 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _arg018 = data.readString();
                    recordRepairmodeAction(_arg018, data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_executeShellCmd /* 31 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    int _arg019 = data.readInt();
                    String _arg16 = data.readString();
                    _arg1 = data.readInt() != 0;
                    executeShellCmd(_arg019, _arg16, _arg1);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_executeShellCmdWithLimitLine /* 32 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    int _arg020 = data.readInt();
                    String _arg17 = data.readString();
                    int _arg25 = data.readInt();
                    String _arg35 = data.readString();
                    boolean _arg45 = data.readInt() != 0;
                    executeShellCmdWithLimitLine(_arg020, _arg17, _arg25, _arg35, _arg45);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_registerShellCmdListener /* 33 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IShellCmdListener _arg021 = IShellCmdListener.Stub.asInterface(data.readStrongBinder());
                    registerShellCmdListener(_arg021);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_unregisterShellCmdListener /* 34 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IShellCmdListener _arg022 = IShellCmdListener.Stub.asInterface(data.readStrongBinder());
                    unregisterShellCmdListener(_arg022);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_executeEncryptSh /* 35 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _arg023 = data.readString();
                    _arg1 = data.readInt() != 0;
                    executeEncryptSh(_arg023, _arg1);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_registerEncryptShListener /* 36 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IEncryptShListener _arg024 = IEncryptShListener.Stub.asInterface(data.readStrongBinder());
                    registerEncryptShListener(_arg024);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_unregisterEncryptShListener /* 37 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IEncryptShListener _arg025 = IEncryptShListener.Stub.asInterface(data.readStrongBinder());
                    unregisterEncryptShListener(_arg025);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_enableAuthMode /* 38 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _arg026 = data.readString();
                    enableAuthMode(_arg026, data.readLong());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_disableAuthMode /* 39 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    disableAuthMode();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAuthMode /* 40 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean authMode = getAuthMode();
                    reply.writeNoException();
                    reply.writeInt(authMode ? 1 : 0);
                    return true;
                case TRANSACTION_getAuthPass /* 41 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    String _result6 = getAuthPass();
                    reply.writeNoException();
                    reply.writeString(_result6);
                    return true;
                case TRANSACTION_getAuthEndTime /* 42 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    long _result7 = getAuthEndTime();
                    reply.writeNoException();
                    reply.writeLong(_result7);
                    return true;
                case TRANSACTION_registerAuthModeListener /* 43 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IAuthModeListener _arg027 = IAuthModeListener.Stub.asInterface(data.readStrongBinder());
                    registerAuthModeListener(_arg027);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_unregisterAuthModeListener /* 44 */:
                    data.enforceInterface("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    IAuthModeListener _arg028 = IAuthModeListener.Stub.asInterface(data.readStrongBinder());
                    unregisterAuthModeListener(_arg028);
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IAfterSalesManager {
            public static IAfterSalesManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "com.xiaopeng.aftersales.manager.IAfterSalesManager";
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void recordDiagnosisError(int module, int errorCode, long millis, String errorMsg, boolean alert) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    try {
                        _data.writeInt(module);
                    } catch (Throwable th) {
                        th = th;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(errorCode);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeLong(millis);
                        try {
                            _data.writeString(errorMsg);
                            _data.writeInt(alert ? 1 : 0);
                            try {
                                boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                                if (!_status && Stub.getDefaultImpl() != null) {
                                    Stub.getDefaultImpl().recordDiagnosisError(module, errorCode, millis, errorMsg, alert);
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                            } catch (Throwable th3) {
                                th = th3;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void updateDiagnosisUploadStatus(int module, boolean status, int errorCode, long millis, String errorMsg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    try {
                        _data.writeInt(module);
                        _data.writeInt(status ? 1 : 0);
                    } catch (Throwable th) {
                        th = th;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(errorCode);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeLong(millis);
                        try {
                            _data.writeString(errorMsg);
                            try {
                                boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                                if (!_status && Stub.getDefaultImpl() != null) {
                                    Stub.getDefaultImpl().updateDiagnosisUploadStatus(module, status, errorCode, millis, errorMsg);
                                    _reply.recycle();
                                    _data.recycle();
                                    return;
                                }
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                            } catch (Throwable th3) {
                                th = th3;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void registerAlertListener(IAlertListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerAlertListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void unregisterAlertListener(IAlertListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterAlertListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void registerDiagnosisStatusListener(IDiagnosisStatusListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerDiagnosisStatusListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void unregisterDiagnosisStatusListener(IDiagnosisStatusListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterDiagnosisStatusListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void recordLogicAction(String issueName, String conclusion, String startTime, String endTime, String logicactionTime, String logicactionEntry, String logictreeVer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeString(issueName);
                    try {
                        _data.writeString(conclusion);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(startTime);
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(endTime);
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(logicactionTime);
                        _data.writeString(logicactionEntry);
                        _data.writeString(logictreeVer);
                        boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().recordLogicAction(issueName, conclusion, startTime, endTime, logicactionTime, logicactionEntry, logictreeVer);
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void updateLogicActionUploadStatus(boolean status, String issueName, String conclusion, String startTime, String endTime, String logicactionTime, String logicactionEntry, String logictreeVer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeInt(status ? 1 : 0);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeString(issueName);
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(conclusion);
                } catch (Throwable th3) {
                    th = th3;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(startTime);
                } catch (Throwable th4) {
                    th = th4;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(endTime);
                    _data.writeString(logicactionTime);
                    _data.writeString(logicactionEntry);
                    _data.writeString(logictreeVer);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateLogicActionUploadStatus(status, issueName, conclusion, startTime, endTime, logicactionTime, logicactionEntry, logictreeVer);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    _reply.readException();
                    _reply.recycle();
                    _data.recycle();
                } catch (Throwable th5) {
                    th = th5;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void requestUploadLogicAction() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestUploadLogicAction();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void requestUpgradeLogicTree(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestUpgradeLogicTree(path);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void registerLogicActionListener(ILogicActionListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerLogicActionListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void unregisterLogicActionListener(ILogicActionListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterLogicActionListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void registerLogicTreeUpgrader(ILogicTreeUpgrader listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerLogicTreeUpgrader(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void unregisterLogicTreeUpgrader(ILogicTreeUpgrader listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterLogicTreeUpgrader(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void enableRepairMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableRepairMode();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void enableRepairModeWithKey(String keyPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeString(keyPath);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableRepairModeWithKey(keyPath);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void enableRepairModeWithKeyId(String keyId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeString(keyId);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableRepairModeWithKeyId(keyId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void disableRepairMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableRepairMode();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public boolean getRepairMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRepairMode();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public String getRepairModeEnableTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getRepairModeEnableTime, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRepairModeEnableTime();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public String getRepairModeDisableTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getRepairModeDisableTime, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRepairModeDisableTime();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public boolean getSpeedLimitMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSpeedLimitMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeedLimitMode();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public String getSpeedLimitEnableTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSpeedLimitEnableTime, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeedLimitEnableTime();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public String getSpeedLimitDisableTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSpeedLimitDisableTime, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeedLimitDisableTime();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public String getRepairModeKeyId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getRepairModeKeyId, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRepairModeKeyId();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void recordSpeedLimitOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_recordSpeedLimitOn, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordSpeedLimitOn();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void recordSpeedLimitOff() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_recordSpeedLimitOff, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordSpeedLimitOff();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void registerRepairModeListener(IRepairModeListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerRepairModeListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerRepairModeListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void unregisterRepairModeListener(IRepairModeListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterRepairModeListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterRepairModeListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void recordRepairmodeAction(String action, String result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeString(action);
                    _data.writeString(result);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_recordRepairmodeAction, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordRepairmodeAction(action, result);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void executeShellCmd(int cmdtype, String param, boolean isCloudCmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeInt(cmdtype);
                    _data.writeString(param);
                    _data.writeInt(isCloudCmd ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_executeShellCmd, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().executeShellCmd(cmdtype, param, isCloudCmd);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void executeShellCmdWithLimitLine(int cmdtype, String param, int limitLine, String quitcmd, boolean isCloudCmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeInt(cmdtype);
                    _data.writeString(param);
                    _data.writeInt(limitLine);
                    _data.writeString(quitcmd);
                    _data.writeInt(isCloudCmd ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_executeShellCmdWithLimitLine, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().executeShellCmdWithLimitLine(cmdtype, param, limitLine, quitcmd, isCloudCmd);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void registerShellCmdListener(IShellCmdListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerShellCmdListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerShellCmdListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void unregisterShellCmdListener(IShellCmdListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterShellCmdListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterShellCmdListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void executeEncryptSh(String path, boolean isCloudCmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeString(path);
                    _data.writeInt(isCloudCmd ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_executeEncryptSh, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().executeEncryptSh(path, isCloudCmd);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void registerEncryptShListener(IEncryptShListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerEncryptShListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerEncryptShListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void unregisterEncryptShListener(IEncryptShListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterEncryptShListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterEncryptShListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void enableAuthMode(String value, long time) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeString(value);
                    _data.writeLong(time);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_enableAuthMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableAuthMode(value, time);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void disableAuthMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_disableAuthMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableAuthMode();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public boolean getAuthMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAuthMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthMode();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public String getAuthPass() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAuthPass, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthPass();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public long getAuthEndTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAuthEndTime, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAuthEndTime();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void registerAuthModeListener(IAuthModeListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerAuthModeListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerAuthModeListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.xiaopeng.aftersales.manager.IAfterSalesManager
            public void unregisterAuthModeListener(IAuthModeListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.xiaopeng.aftersales.manager.IAfterSalesManager");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterAuthModeListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterAuthModeListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAfterSalesManager impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAfterSalesManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
