package com.pablobaldez.guga.view;

import com.trello.navi.Event;
import com.trello.navi.NaviComponent;
import com.trello.navi.component.NaviFragment;
import com.trello.navi.rx.RxNavi;

/**
 * @author Pablo
 * @since 26/05/2016
 */
public class GugaViewDelegate {

    private GugaViewDelegate(NaviComponent naviComponent) {
        RxNavi.observe(naviComponent, Event.CREATE).subscribe(
                bundle -> {

                }
        );
    }

    private GugaViewDelegate(NaviFragment fragment) {

    }


}
