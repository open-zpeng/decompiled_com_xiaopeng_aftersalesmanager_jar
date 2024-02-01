package com.xiaopeng.aftersales.manager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IDiagnosisStatusListener extends IInterface {
    void onDiagnosisStatusChanged(int i, int i2, long j, String str, int i3) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IDiagnosisStatusListener {
        @Override // com.xiaopeng.aftersales.manager.IDiagnosisStatusListener
        public void onDiagnosisStatusChanged(int module, int errorCode, long time, String errorMsg, int status) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDiagnosisStatusListener {
        private static final String DESCRIPTOR = "com.xiaopeng.aftersales.manager.IDiagnosisStatusListener";
        static final int TRANSACTION_onDiagnosisStatusChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDiagnosisStatusListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IDiagnosisStatusListener)) {
                return (IDiagnosisStatusListener) iin;
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
            int _arg0 = data.readInt();
            int _arg1 = data.readInt();
            long _arg2 = data.readLong();
            String _arg3 = data.readString();
            int _arg4 = data.readInt();
            onDiagnosisStatusChanged(_arg0, _arg1, _arg2, _arg3, _arg4);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDiagnosisStatusListener {
            public static IDiagnosisStatusListener sDefaultImpl;
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

            @Override // com.xiaopeng.aftersales.manager.IDiagnosisStatusListener
            public void onDiagnosisStatusChanged(int module, int errorCode, long time, String errorMsg, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeInt(module);
                    try {
                        _data.writeInt(errorCode);
                    } catch (Throwable th2) {
                        th = th2;
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeLong(time);
                    } catch (Throwable th3) {
                        th = th3;
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(errorMsg);
                        try {
                            _data.writeInt(status);
                            try {
                                boolean _status = this.mRemote.transact(1, _data, null, 1);
                                if (!_status && Stub.getDefaultImpl() != null) {
                                    Stub.getDefaultImpl().onDiagnosisStatusChanged(module, errorCode, time, errorMsg, status);
                                    _data.recycle();
                                    return;
                                }
                                _data.recycle();
                            } catch (Throwable th4) {
                                th = th4;
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
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

        public static boolean setDefaultImpl(IDiagnosisStatusListener impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IDiagnosisStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
