package com.xiaopeng.aftersales.manager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ILogicActionListener extends IInterface {
    void uploadLogicAction(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements ILogicActionListener {
        @Override // com.xiaopeng.aftersales.manager.ILogicActionListener
        public void uploadLogicAction(String issueName, String conclusion, String startTime, String endTime, String logicactionTime, String logicactionEntry, String logictreeVer) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements ILogicActionListener {
        private static final String DESCRIPTOR = "com.xiaopeng.aftersales.manager.ILogicActionListener";
        static final int TRANSACTION_uploadLogicAction = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILogicActionListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ILogicActionListener)) {
                return (ILogicActionListener) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            String _arg0 = data.readString();
            String _arg1 = data.readString();
            String _arg2 = data.readString();
            String _arg3 = data.readString();
            String _arg4 = data.readString();
            String _arg5 = data.readString();
            String _arg6 = data.readString();
            uploadLogicAction(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements ILogicActionListener {
            public static ILogicActionListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.xiaopeng.aftersales.manager.ILogicActionListener
            public void uploadLogicAction(String issueName, String conclusion, String startTime, String endTime, String logicactionTime, String logicactionEntry, String logictreeVer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeString(issueName);
                } catch (Throwable th2) {
                    th = th2;
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(conclusion);
                } catch (Throwable th3) {
                    th = th3;
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(startTime);
                } catch (Throwable th4) {
                    th = th4;
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(endTime);
                    try {
                        _data.writeString(logicactionTime);
                    } catch (Throwable th5) {
                        th = th5;
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(logicactionEntry);
                        _data.writeString(logictreeVer);
                        boolean _status = this.mRemote.transact(1, _data, null, 1);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().uploadLogicAction(issueName, conclusion, startTime, endTime, logicactionTime, logicactionEntry, logictreeVer);
                            _data.recycle();
                            return;
                        }
                        _data.recycle();
                    } catch (Throwable th6) {
                        th = th6;
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _data.recycle();
                    throw th;
                }
            }
        }

        public static boolean setDefaultImpl(ILogicActionListener impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ILogicActionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
