package com.pablobaldez.guga.subscribers;

import com.pablobaldez.guga.view.GugaListMvpView;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Create basics subscriber to change the view state
 * @author pablobaldez
 * @since 7/12/16
 */
public final class GugaSubscribers {

    public static <T> Subscriber<T> toPagination(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        return new GugaPaginationSubscriber(view, onCompleteList);
    }

    public static <T> Subscriber<T> toAddNews(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        return new GugaAddNewSubscriber<>(view, onCompleteList);
    }

    public static <T> Subscriber<T> toAddNews(GugaListMvpView view, Action1<List<T>> onCompleteList, int initialPosition) {
        return new GugaAddNewSubscriber<>(view, onCompleteList, initialPosition);
    }

    public static <T> Subscriber<T> toRefresh(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        return new GugaRefreshListSubscriber<>(view, onCompleteList);
    }

    public static <T> Subscriber<T> toChange(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        return new GugaChangeItemSubscriber<>(view, onCompleteList);
    }

    public static <T> Subscriber<T> toChange(GugaListMvpView view, Action1<List<T>> onCompleteList, int initialPosition) {
        return new GugaChangeItemSubscriber<>(view, onCompleteList, initialPosition);
    }

    public static <T> Subscriber<T> toRemove(GugaListMvpView view, Action1<List<T>> onCompleteList) {
        return new GugaRemoveItemSubscriber<>(view, onCompleteList);
    }

    public static <T> Subscriber<T> toRemove(GugaListMvpView view, Action1<List<T>> onCompleteList, int initialPosition) {
        return new GugaRemoveItemSubscriber<>(view, onCompleteList, initialPosition);
    }

    private GugaSubscribers() {
        // disable instances
    }

}
