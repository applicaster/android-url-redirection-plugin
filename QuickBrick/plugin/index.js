import {RedirectUrlComponent} from "./src"

const RedirectUrlPlugin = {
  presentFullScreen: true,
  hasPlayerHook: true,
  isFlowBlocker: () => true,
  Component: RedirectUrlComponent
};

export default RedirectUrlPlugin
