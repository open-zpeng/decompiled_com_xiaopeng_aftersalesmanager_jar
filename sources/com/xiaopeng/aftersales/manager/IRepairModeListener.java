package com.xiaopeng.aftersales.manager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IRepairModeListener extends IInterface {
    void onRepairModeChanged(boolean z, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IRepairModeListener {
        @Override // com.xiaopeng.aftersales.manager.IRepairModeListener
        public void onRepairModeChanged(boolean onoff, int switchResult) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IRepairModeListener {
        private static final String DESCRIPTOR = "com.xiaopeng.aftersales.manager.IRepairModeListener";
        static final int TRANSACTION_onRepairModeChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRepairModeListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IRepairModeListener)) {
                return (IRepairModeListener) iin;
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
            boolean _arg0 = data.readInt() != 0;
            int _arg1 = data.readInt();
            onRepairModeChanged(_arg0, _arg1);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IRepairModeListener {
            public static IRepairModeListener sDefaultImpl;
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

            @Override // com.xiaopeng.aftersales.manager.IRepairModeListener
            public void onRepairModeChanged(boolean onoff, int switchResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(onoff ? 1 : 0);
                    _data.writeInt(switchResult);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRepairModeChanged(onoff, switchResult);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRepairModeListener impl) {
            if (Proxy.sDefaultImpl == null && impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IRepairModeListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
