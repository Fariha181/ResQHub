package com.resqhub.resqhub.observer;

import org.springframework.stereotype.Component;

@Component
public class AgencyNotifier implements IncidentObserver {

    @Override
    public void onIncidentCreated(String title, String location, String agency) {
        //  প্রজেক্টে এখান থেকে ইমেইল/এসএমএস/সকেট নোটিফিকেশন যায়
        System.out.println("ALERT [" + agency + " Agency]: New emergency reported -> " + title + " at " + location);
    }
}