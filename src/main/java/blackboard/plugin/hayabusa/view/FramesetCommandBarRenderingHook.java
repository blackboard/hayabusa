package blackboard.plugin.hayabusa.view;

import blackboard.platform.intl.BbResourceBundle;
import blackboard.platform.intl.BundleManagerFactory;
import blackboard.platform.plugin.PlugIn;
import blackboard.platform.plugin.PlugInManagerFactory;
import blackboard.platform.servlet.JspResourceIncludeUtil;
import blackboard.servlet.renderinghook.RenderingHookKey;

public class FramesetCommandBarRenderingHook extends CommandBarRenderingHook
{
  private static final String DIV_START = "<div id=\"light\" class=\"lightboxContent\">";
  private static final String DIV_END = "</div>";
  private static final String INPUT_FIELD = "<input id=\"lightboxInput\" type=\"text\" x-webkit-speech autofocus ></input>";
  private static final String SEARCH_ICON = "<div id=\"hayabusa-search-wrapper\" class=\"hayabusa-search-wrapper\">"
                                            + "<a id=\"hayabusa-search_link\" class=\"hayabusa-search_link\" href=\"#\" onclick=\"top.document.getElementById('light').style.display='block';return false;\">Search</a>"
                                            + "</div>";

  @Override
  public String getContent()
  {
    String uriPrefix = getUriPrefix();
    JspResourceIncludeUtil resourceIncludeUtil = JspResourceIncludeUtil.getThreadInstance();
    resourceIncludeUtil.addJsFile( uriPrefix + "js/jquery-1.9.1.js" );
    resourceIncludeUtil.addJsFile( uriPrefix + "js/jquery-ui.js" );
    resourceIncludeUtil.addCssFile( uriPrefix + "css/jquery-ui.css" );
    resourceIncludeUtil.addCssFile( uriPrefix + "css/hayabusa-main.css" );
    resourceIncludeUtil.addJsFile( uriPrefix + "js/mousetrap.min.js" );
    resourceIncludeUtil.addJsFile( uriPrefix + "js/mousetrap-global-bind.min.js" );
    resourceIncludeUtil.addJsFile( uriPrefix + "js/hayabusa-main.js" );
    resourceIncludeUtil.addJsFile( uriPrefix + "js/hayabusa-shortcutkeys.js" );

    PlugIn plugin = PlugInManagerFactory.getInstance().getPlugIn( "bb", "hayabusa" );
    BbResourceBundle bundle = BundleManagerFactory.getInstance().getPluginBundle( plugin.getId() );
    resourceIncludeUtil.addJsBundleMessage( bundle, "command.category.course" );
    resourceIncludeUtil.addJsBundleMessage( bundle, "command.category.language.pack" );
    resourceIncludeUtil.addJsBundleMessage( bundle, "command.category.my.course" );
    resourceIncludeUtil.addJsBundleMessage( bundle, "command.category.system.admin" );
    resourceIncludeUtil.addJsBundleMessage( bundle, "command.category.user" );
    return constructForm();
  }

  private String constructForm()
  {
    return DIV_START + INPUT_FIELD + DIV_END + SEARCH_ICON;
  }

  @Override
  public String getKey()
  {
    return RenderingHookKey.Frameset.getKey();
  }
}
