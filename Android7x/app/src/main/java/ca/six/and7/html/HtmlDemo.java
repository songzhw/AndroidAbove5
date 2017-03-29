package ca.six.and7.html;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

/**
 * Created by songzhw on 2017-03-28
 */

public class HtmlDemo extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setTextSize(30);
        setContentView(tv);

        // 1. just text
//        String htmlStr = "<html><body><b>This text is bold</b><br/> <i>This text is italic</i><br/> Html<sup>005</sup></body></html>";
//        Spanned spanned = Html.fromHtml(htmlStr, 0);
//        tv.setText(spanned);


        // 2.
        String htmlStr2 = "<html><body>" +
                "<b>This text is bold</b><br/> " +
                "<i>This text is italic</i><br/> " +
                "Html<sup>005</sup><br/>" +
                "<img src=\"https://static-s.aa-cdn.net/img/gp/20600002622696/dYjJdGSa9_TPfO4XrMaCbOwKzqoS1hLpIHIWnEUXyMc4LgHLfJKajA8LGlxgDZ9jSwk=w300?v=1\"/>" +
                "</body></html>";
        Spanned spanned2 = Html.fromHtml(htmlStr2, 0);
        tv.setText(spanned2);
    }
}
