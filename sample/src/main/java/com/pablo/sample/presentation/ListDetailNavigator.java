package com.pablo.sample.presentation;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.pablo.sample.domain.User;
import com.pablobaldez.guga.navigation.ActivityResultChecker;
import com.pablobaldez.guga.navigation.GugaNavigationExtractor;
import com.pablobaldez.guga.navigation.GugaNavigationResultFinisher;
import com.trello.navi.model.ActivityResult;

/**
 * @author pablobaldez
 * @since 7/18/16
 */
public interface ListDetailNavigator  {

    int REQUEST_CODE = 12;
    String NAME_KEY = "name_key";
    String EMAIL_KEY = "email_key";

    final class ListDetailNavigation extends GugaNavigationExtractor<User> {

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

    final class DetailResultResultFinisher extends GugaNavigationResultFinisher<User> {

        public DetailResultResultFinisher(Activity activity) {
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

}
