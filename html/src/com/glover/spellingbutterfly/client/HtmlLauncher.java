package com.glover.spellingbutterfly.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.glover.spellingbutterfly.SpellingButterfly;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
                //return new GwtApplicationConfiguration(true);
                // Fixed size application:
                return new GwtApplicationConfiguration(1920, 1200);
        }



        @Override
        public ApplicationListener createApplicationListener () {
                return new SpellingButterfly();
        }
}