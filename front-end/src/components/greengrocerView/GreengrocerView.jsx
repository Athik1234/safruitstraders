import React from 'react';
import { HashRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import {
  Grid
} from '@material-ui/core';
import GreengrocerBar from "./sidebar/GreengrocerBar";
import GreengrocerOrder from "./mainpage/GreengrocerOrder";
import GreengrocerHome from "./mainpage/GreengrocerHome";
import GreengrocerHistory from "./mainpage/GreengrocerHistory";
import GreengrocerInfo from "./mainpage/GreengrocerInfo";
import Greengrocerlist from "./mainpage/Greengrocerlist";

class GreengrocerView extends React.Component {
  constructor(props) {
    super(props);
    this.props.changeView("Greengrocer")
  }
  render() {
    return this.props.currentUser ? (
      <Router>
        <Grid container justify="flex-start">
          <Grid item sm={3}>
            <GreengrocerBar />
          </Grid>
          <Grid item sm={9}>
            <div className="grid-item">
              <Switch>
                <Route path="/greengrocer/home" render={props => <GreengrocerHome {...props} currentUser={this.props.currentUser} />} />
                <Route path="/greengrocer/information" render={props => <GreengrocerInfo {...props} currentUser={this.props.currentUser} />} />
                <Route path="/greengrocer/list" render={props => <Greengrocerlist {...props} currentUser={this.props.currentUser} />} />
                <Route path="/greengrocer/order" render={props => <GreengrocerOrder {...props} currentUser={this.props.currentUser} />} />
                <Route path="/greengrocer/history" render={props => <GreengrocerHistory {...props} currentUser={this.props.currentUser} />} />
                <Redirect path="/greengrocer" to="/greengrocer/home" />
              </Switch>
            </div>
          </Grid>
        </Grid>
      </Router>
    ) : <div />;
  }
}

export default GreengrocerView;