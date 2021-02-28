import React from 'react';
import {
  Grid
} from '@material-ui/core';
import ItemCard from "../../card/ItemCard";
import Emptyitem from '../../card/Emptyitem';
const axios = require('axios').default;

class Greengrocerlist extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      list : undefined
    }
    this.getAllitems = this.getAllitems.bind(this);
  }

  componentDidMount() {
    this.getAllitems();
  }

  getAllitems() {
    axios.get("/api/greengrocer/list/" + this.props.currentUser.id).then(
      response => {
        this.setState({list: response.data});
      }
    ).catch(err => console.log(err));
  }

  render() {
    return this.props.currentUser ? (
      <Grid container spacing={3} justify="space-evenly">
        {this.state.list ? 
          (this.state.list.map((item, index) => (
            <Grid item xs={3} key={index}>
              <ItemCard item={item} getAllitems={this.getAllitems} currentUser={this.props.currentUser} />
            </Grid>
          ))) : null
        }
        <Grid item xs={3}>
          <Emptyitem getAllitems={this.getAllitems} currentUser={this.props.currentUser} />
        </Grid>
      </Grid>
    ) : <div>The Greengrocer is not available</div>
  }
}

export default Greengrocerlist;