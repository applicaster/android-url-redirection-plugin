//@flow
import * as React from "react"
import axios from "axios"
import {View} from "react-native"
import * as R from "ramda"

type Props = {
  configuration: {},
  payload: {},
  callback: ({ success: boolean, error: ?{}, payload: ?{} }) => void
};

type State = {}

const videoSourcePath = R.lensPath(["content", "src"]);

export class RedirectUrlComponent extends React.Component<Props, State> {

  state = {};

  componentDidMount() {
    const { payload, callback } = this.props;

    const videoContentUrl = R.view(videoSourcePath, payload);
    if (!videoContentUrl) {
      callback({ success: false, error: { message: "Video source doesn't exists by this path" } })
    }

    axios.get(videoContentUrl)
      .then(function(response) {
        const responseUrl = R.path(["request", "responseURL"], response);
        const redirectedPayload = R.set(videoSourcePath, responseUrl, payload);
        callback({ success: true, payload: redirectedPayload })
      })
      .catch(function(error) {
        callback({ success: false, error })
      })
  }

  render() {
    return (<View style={{ flex: 1 }}/>);
  }
}
