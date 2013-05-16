package blackboard.plugin.hayabusa.view;

import blackboard.persist.PersistenceException;
import blackboard.platform.context.ContextManagerFactory;
import blackboard.platform.context.UnsetContextException;
import blackboard.platform.plugin.*;
import blackboard.platform.servlet.JspResourceIncludeUtil;
import blackboard.platform.vxi.data.VirtualInstallation;
import blackboard.platform.vxi.service.VirtualSystemException;
import blackboard.servlet.renderinghook.RenderingHook;
import blackboard.servlet.renderinghook.RenderingHookKey;

import org.springframework.stereotype.Component;

/**
 * Command bar rendering hook for Hayabusa
 * 
 * @author Noriaki Tatsumi
 * @since 1.0
 */
@Component
public class CommandBarRenderingHook implements RenderingHook
{
  public static final String HANDLE = "hayabusa";
  public static final String VENDOR = "bb";

  private static final String INPUT_FIELD = "<div id=\"light\" class=\"lightbox_content\"><input id=\"lightbox_input\" type=\"text\" x-webkit-speech /></div>";

  @Override
  public String getContent()
  {
    String uriPrefix = "";
    try
    {
      uriPrefix = getUriPrefix();
    }
    catch ( VirtualSystemException | PersistenceException | UnsetContextException e )
    {
      // TODO can we handle this better?
      e.printStackTrace();
    }
    JspResourceIncludeUtil resourceIncludeUtil = JspResourceIncludeUtil.getThreadInstance();
    resourceIncludeUtil.addCssFile( uriPrefix + "css/hayabusa-main.css" );
    resourceIncludeUtil.addJsFile( uriPrefix + "js/mousetrap.min.js" );
    resourceIncludeUtil.addJsFile( uriPrefix + "js/hayabusa-shortcutkeys.js" );
    return INPUT_FIELD;
  }

  private static String getUriPrefix() throws VirtualSystemException, PersistenceException, UnsetContextException
  {
    PlugIn plugIn = PlugInManagerFactory.getInstance().getPlugIn( VENDOR, HANDLE );
    VirtualInstallation vi = ContextManagerFactory.getInstance().getContext().getVirtualInstallation();
    return PlugInUtil.getUriStem( plugIn, vi );
  }

  @Override
  public String getKey()
  {
    return RenderingHookKey.Frameset.getKey();
    //return RenderingHookKey.TopFrame.getKey();
  }
}
