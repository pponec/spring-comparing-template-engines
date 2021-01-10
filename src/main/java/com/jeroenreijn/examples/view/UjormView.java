package com.jeroenreijn.examples.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractTemplateView;

import org.ujorm.tools.web.Element;
import org.ujorm.tools.web.HtmlElement;
import com.jeroenreijn.examples.model.Presentation;
import org.ujorm.tools.xml.config.HtmlConfig;

public class UjormView extends AbstractTemplateView {

    private static final String BOOTSTRAP_CSS_LINK = "/webjars/bootstrap/4.3.1/css/bootstrap.min.css";
    private static final String BOOTSTRAP_JS_LINK = "/webjars/bootstrap/4.3.1/js/bootstrap.min.js";
    private static final String JQUERY_LINK = "/webjars/jquery/3.1.1/jquery.min.js";
    private static final HtmlConfig CONFIG = (HtmlConfig) HtmlConfig.ofDefault()
            .setTitle("JFall 2013 Presentations - Ujorm")
            .setNiceFormat("\t");

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> map, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        Iterable<Presentation> presentations = (Iterable<Presentation>) map.get("presentations");

        try ( HtmlElement html = HtmlElement.of(response, CONFIG)) {
            try ( Element head = html.getHead()) {
                head.addElement("meta").setAttrib("name", "viewport").setAttrib("content", "width=device-width, initial-scale=1.0");
                head.addElement("meta").setAttrib("http-equiv", "X-UA-Compatible").setAttrib("content", "IE=Edge");
                head.addElement("link").setAttrib("rel", "stylesheet").setAttrib("href", BOOTSTRAP_CSS_LINK).setAttrib("media", "screen");
            }
            try ( Element body = html.getBody()) {
                try ( Element container = body.addDiv("container")) {
                    container.addDiv("pb-2", "mt-4", "mb-3", "border-bottom")
                            .addHeading(html.getTitle());
                    presentations.forEach(presentation -> {
                        try ( Element card = container.addDiv("card", "mb-3", "shadow-sm", "rounded")) {
                            card.addDiv("card-header")
                                    .addHeading(5, "", "card-title")
                                    .addText(presentation.getTitle(), " - ", presentation.getSpeakerName());
                            card.addDiv("card-body")
                                    .addRawText(presentation.getSummary());
                        }
                    });
                }
                body.addScript().setAttrib("src", JQUERY_LINK).addText("");
                body.addScript().setAttrib("src", BOOTSTRAP_JS_LINK).addText("");
            }
        }
    }

}
