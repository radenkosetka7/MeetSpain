package com.example.upoznajspaniju.wrapper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

public class MyWrapper extends ContextWrapper {

    public MyWrapper(Context context)
    {
    super(context);
    }

        public static ContextWrapper wrap(Context context, String language){
            Configuration configuration = context.getResources().getConfiguration();
            Locale sysLocale = null;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                sysLocale = getSystemLocale(configuration);
            }else{
                sysLocale =  getSysytemLocaleLegacy(configuration);
            }
            if(!language.equals("") && !sysLocale.getLanguage().equals(language)){
                Locale locale = new Locale(language);
                Locale.setDefault(locale);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    setSystemLocale(configuration, locale);
                }else{
                    setSystemLocaleLegacy(configuration, locale);
                }
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                    context = context.createConfigurationContext(configuration);
                }else {
                    context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
                }
            }
            return new MyWrapper(context);
        }

        private static Locale getSysytemLocaleLegacy(Configuration configuration) {
            return configuration.locale;
        }

        @TargetApi(Build.VERSION_CODES.N)
        private static void setSystemLocale(Configuration configuration, Locale locale) {
            configuration.setLocale(locale);
        }

        private static void setSystemLocaleLegacy(Configuration configuration, Locale locale) {
            configuration.locale = locale;
        }

        @TargetApi(Build.VERSION_CODES.N)
        public static Locale getSystemLocale(Configuration configuration) {
            return configuration.getLocales().get(0);
        }
}
