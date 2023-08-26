//package com.bitspondon.quiz.presentation.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.togglz.core.Feature;
//import org.togglz.core.annotation.EnabledByDefault;
//import org.togglz.core.annotation.Label;
//import org.togglz.core.manager.EnumBasedFeatureProvider;
//import org.togglz.core.manager.TogglzConfig;
//import org.togglz.core.repository.StateRepository;
//import org.togglz.core.spi.FeatureProvider;
//import org.togglz.core.user.FeatureUser;
//import org.togglz.core.user.SimpleFeatureUser;
//import org.togglz.core.user.UserProvider;
//import org.togglz.servlet.util.HttpServletRequestHolder;
//
//@Component
//public class FeatureTogglzConfig implements TogglzConfig {
//
//    /* ..... */
//
//    @Override
//    public Class<? extends Feature> getFeatureClass() {
//        return null;
//    }
//
//    @Override
//    public StateRepository getStateRepository() {
//        return null;
//    }
//
//    @Override
//    public UserProvider getUserProvider() {
//        return new UserProvider() {
//
//            @Override
//            public FeatureUser getCurrentUser() {
//
//                HttpServletRequest request = HttpServletRequestHolder.get();
//
//                String username = (String) request.getAttribute("username");
//                boolean isAdmin = "admin".equals(username);
//
//                return new SimpleFeatureUser(username, isAdmin);
//
//            }
//        };
//    }
//
//    public enum MyFeatures implements Feature {
//
//        @EnabledByDefault
//        @Label("First Feature")
//        FEATURE_ONE,
//
//        @Label("Second Feature")
//        FEATURE_TWO;
//    }
//
//    @Bean
//    public FeatureProvider featureProvider() {
//        return new EnumBasedFeatureProvider(MyFeatures.class);
//    }
//}
