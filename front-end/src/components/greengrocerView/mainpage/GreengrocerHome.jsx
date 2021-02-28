import React from 'react';
import { Typography, Divider } from '@material-ui/core';
const axios = require('axios').default;

class GreengrocerHome extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      greengrocer : undefined
    }
    this.getGreengrocer = this.getGreengrocer.bind(this);
  }

  componentDidMount() {
    this.getGreengrocer();
  }

  getGreengrocer() {
    let greengrocerId = this.props.currentUser.id;
    axios.get("/api/greengrocer/" + greengrocerId).then(
      response => {
        this.setState({greengrocer : response.data});
      }
    ).catch(err => console.log(err));
  }

  render() {
    return this.state.greengrocer ? (
      <div>
        <Typography paragraph variant="h5">Welcome to the <i><b>FreshMart</b></i> !</Typography>
        <Typography paragraph>If you are a new user, please provide your greengrocer information and list using the links in sidebar</Typography>
        <Typography paragraph>Once you finish them, your greengrocer will be visible to the customers</Typography>
        <Typography paragraph>To be noticed, you can always update them</Typography>
        <Typography paragraph>Enjoy!!!</Typography>
        <Divider />
        <br />
        <div>Greengrocer Information status : 
        {this.state.greengrocer.information !== null ? <Typography color="primary">verified</Typography> : <Typography color="error">empty</Typography>}
        </div>
        <br />
        <div>List status : 
        {this.state.greengrocer.list && this.state.greengrocer.list.length !== 0 ? <Typography color="primary">verified</Typography> : <Typography color="error">empty</Typography>}
        </div>
      </div>
    ) : <div />;
  }
}

export default GreengrocerHome;