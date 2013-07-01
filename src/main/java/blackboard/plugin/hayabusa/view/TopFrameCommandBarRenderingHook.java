package blackboard.plugin.hayabusa.view;

import blackboard.platform.servlet.JspResourceIncludeUtil;
import blackboard.servlet.renderinghook.RenderingHookKey;

public class TopFrameCommandBarRenderingHook extends CommandBarRenderingHook
{
  @Override
  public String getContent()
  {
    String uriPrefix = getUriPrefix();
    JspResourceIncludeUtil resourceIncludeUtil = JspResourceIncludeUtil.getThreadInstance();
    resourceIncludeUtil.addJsFile( uriPrefix + "js/mousetrap.min.js" );
    resourceIncludeUtil.addJsFile( uriPrefix + "js/mousetrap-global-bind.min.js" );
    resourceIncludeUtil.addJsFile( uriPrefix + "js/hayabusa-shortcutkeys.js" );
    return "";
  }

  @Override
  public String getKey()
  {
    return RenderingHookKey.TopFrame.getKey();
  }
}
