import {RedirectUrlComponent} from "./src"

const RedirectUrlPlugin = {
  presentFullScreen: true,
  isFlowBlocker: () => true,
  hasPlayerHook: true,
  Component: RedirectUrlComponent
};

export default RedirectUrlPlugin
