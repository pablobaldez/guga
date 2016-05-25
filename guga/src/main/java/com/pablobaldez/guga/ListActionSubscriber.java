package com.pablobaldez.guga;

import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by pablobaldez on 5/12/16.
 * Subscriber to run a list of actions for each event triggered from observables
 */
public class ListActionSubscriber<T> extends Subscriber<T> {

    private final List<Action0> onStartActions;
    private final List<Action1<T>> onNextActions;
    private final List<Action0> onCompletedActions;
    private final List<Action1<Throwable>> onErrorActions;

    public ListActionSubscriber(){
        onStartActions = new LinkedList<>();
        onNextActions = new LinkedList<>();
        onCompletedActions = new LinkedList<>();
        onErrorActions = new LinkedList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        runActions0(onStartActions);
    }

    @Override
    public void onCompleted() {
        runActions0(onCompletedActions);
    }

    @Override
    public void onError(Throwable e) {
        runActions1(onErrorActions, e);
    }

    @Override
    public void onNext(T t) {
        runActions1(onNextActions, t);
    }

    /**
     * Add an action to be called when subscriber starts
     * @param action action that will be called
     * @return this
     */
    public ListActionSubscriber<T> addOnStartAction(Action0 action) {
        onStartActions.add(action);
        return this;
    }

    /**
     * Add an action to be called when subscriber emit items
     * @param action action that will be called
     * @return this
     */
    public ListActionSubscriber<T> addOnNextAction(Action1<T> action) {
        onNextActions.add(action);
        return this;
    }

    /**
     * Add an action to be called when subscriber completes
     * @param action action that will be called
     * @return this
     */
    public ListActionSubscriber<T> addOnCompletedAction(Action0 action) {
        onCompletedActions.add(action);
        return this;
    }

    /**
     * Add an action to be called when subscriber emit errors
     * @param action action that will be called
     * @return this
     */
    public ListActionSubscriber<T> addOnErrorAction(Action1<Throwable> action) {
        onErrorActions.add(action);
        return this;
    }

    /**
     * run actions
     * @param actions actions that will be ran
     */
    public static void runActions0(Iterable<Action0> actions){
        for(Action0 action: actions) {
            action.call();
        }
    }

    /**
     * run actions
     * @param actions  actions that will be ran
     * @param actionParam param of actions
     * @param <T> type of params to send to this actions
     */
    public static <T> void runActions1(Iterable<Action1<T>> actions, T actionParam) {
        for(Action1<T> action: actions) {
            action.call(actionParam);
        }
    }

}
