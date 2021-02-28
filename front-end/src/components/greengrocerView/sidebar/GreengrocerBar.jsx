import React from 'react';
import { Link } from "react-router-dom";
import {
  List,
  ListItem,
  ListItemText,
  Divider
}
from '@material-ui/core';

class GreengrocerBar extends React.Component {
  render() {
    return (
      <div>
        <br />
        <h3><b><i>Greengrocer ToolBar</i></b></h3>
        <br />
        <List component="nav">
          <Link to={"/greengrocer/home"} className="link">
            <ListItem>
              <ListItemText primary={"Home Page"} />
            </ListItem>
            <Divider />
          </Link>
          <Link to={"/greengrocer/information"} className="link">
            <ListItem>
              <ListItemText primary={"Greengrocer Information"} />
            </ListItem>
            <Divider />
          </Link>
          <Link to={"/greengrocer/list"} className="link">
            <ListItem>
              <ListItemText primary={"List"} />
            </ListItem>
            <Divider />
          </Link>
          <Link to={"/greengrocer/order"} className="link">
            <ListItem>
              <ListItemText primary={"My Active Orders"} />
            </ListItem>
            <Divider />
          </Link>
          <Link to={"/greengrocer/history"} className="link">
            <ListItem>
              <ListItemText primary={"My Order History"} />
            </ListItem>
            <Divider />
          </Link>
        </List>
      </div>
    );
  }
}

export default GreengrocerBar;