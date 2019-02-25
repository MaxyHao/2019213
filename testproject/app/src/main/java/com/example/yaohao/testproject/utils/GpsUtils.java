package com.example.yaohao.testproject.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * Created by yaohao on 2019/2/25.
 */

/**
 * Created by Administrator on 2018/4/17.
 * 获取用户的地理位置
 */
public class GpsUtils {

    private static GpsUtils instance;
    private Context mContext;
    private LocationManager locationManager;

    private GpsUtils(Context context) {
        this.mContext = context;
    }

    public static GpsUtils getInstance(Context context) {
        if (instance == null) {
            instance = new GpsUtils(context);
        }
        return instance;
    }

    /**
     * 获取经纬度
     *
     * @return
     */
    public String getLngAndLat(OnLocationResultListener onLocationResultListener) {
        double latitude = 0.0;
        double longitude = 0.0;

        mOnLocationListener = onLocationResultListener;

        String locationProvider = null;
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);

        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Intent i = new Intent();
            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            mContext.startActivity(i);
            return null;
        }


        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            //不为空,显示地理位置经纬度
            if (mOnLocationListener != null) {
                mOnLocationListener.onLocationResult(location);
            }

        }
        //监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
        return null;
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            String addString = null;
            List<Address> addList = null;
            Geocoder ge = new Geocoder(mContext);
            try {
                addList = ge.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addList != null && addList.size() > 0) {
                for (int i = 0; i < addList.size(); i++) {
                    Address ad = addList.get(i);
                    addString = ad.getLocality();//拿到城市
                    mOnLocationListener.onLocationCity(ad);
                }
                String locationStr = "维度：" + location.getLatitude() + "经度：" + location.getLongitude();
            }

        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    public void removeListener() {
        locationManager.removeUpdates(locationListener);
    }

    private OnLocationResultListener mOnLocationListener;

    public interface OnLocationResultListener {
        void onLocationResult(Location location);

        void onLocationCity(Address ad);

        void OnLocationChange(Location location);
    }


}