package com.pablo.sample.presentation;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.pablo.sample.domain.User;
import com.pablobaldez.guga.presenter.ActivityResultChecker;
import com.pablobaldez.guga.presenter.GugaNavigationExtractor;
import com.pablobaldez.guga.presenter.GugaNavigationFinisher;
import com.trello.navi.model.ActivityResult;

/**
 * @author pablobaldez
 * @since 7/18/16
 */
public class ListDetailNavigator  {

    private static final int REQUEST_CODE = 12;
    private static final String NAME_KEY = "name_key";
    private static final String EMAIL_KEY = "email_key";

    public static final class ListDetailNavigation extends GugaNavigationExtractor<User> {

        private final ListUserFragment fragment;

        public ListDetailNavigation(ListUserFragment fragment) {
            super(fragment);
            this.fragment = fragment;
        }

        @Override
        public void startForResult() {
            Activity activity = fragment.getActivity();
            Intent intent = new Intent(activity, DetailUserActivity.class);
            fragment.startActivityForResult(intent, REQUEST_CODE);
        }

        @Nullable
        @Override
        public User mapData(ActivityResult activityResult) {
            ActivityResultChecker checker = new ActivityResultChecker(activityResult);
            if(checker.activityResponseOk(REQUEST_CODE, NAME_KEY, EMAIL_KEY)) {
                Intent data = activityResult.data();
                return new User(data.getStringExtra(NAME_KEY), data.getStringExtra(EMAIL_KEY));
            }
            return null;
        }
    }

    public static final class DetailResultFinisher extends GugaNavigationFinisher<User> {

        public DetailResultFinisher(Activity activity) {
            super(activity);
        }

        @Override
        public Intent mapToIntent(User data) {
            Intent intent = new Intent();
            intent.putExtra(NAME_KEY, data.name);
            intent.putExtra(EMAIL_KEY, data.email);
            return intent;
        }
    }

    private ListDetailNavigator(){}

}
