import React from 'react';
import Paper from '@material-ui/core/Paper';
import InputBase from '@material-ui/core/InputBase';
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import {
  Grid, Typography
} from '@material-ui/core';
import GreengrocerCard from "../../card/GreengrocerCard";
import "./Customer.css";
const axios = require('axios').default;

class CustomerHome extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchText: "",
      greengrocer: undefined
    }
    this.handleChange = this.handleChange.bind(this);
    this.findGreengrocers = this.findGreengrocers.bind(this);
    this.handleSearch = this.handleSearch.bind(this);
  }

  componentDidMount() {
    this.findGreengrocers(this.searchText);
  }

  findGreengrocers(query) {
    if (query !== undefined && query !== "") {
      axios.get("/api/greengrocer/search/" + query).then(
        response => {
          this.setState({greengrocer: response.data})
        }
      ).catch(err => console.log(err));
    } else {
      axios.get("/api/greengrocer/all").then(
        response => {
          let temp = response.data.filter(greengrocer => greengrocer.information != null && greengrocer.list != null);
          this.setState({searchText: "", greengrocer: temp});
        }
      ).catch(err => console.log(err));
    }
  }

  handleChange(content) {
    this.setState(content);
  }

  handleSearch(event) {
    event.preventDefault();
    this.findGreengrocers(this.state.searchText);
  }

  render() {
    return this.props.currentUser ? (
      <div>
        <Grid container justify="center">
          <Grid item>
            <Paper component="form" onSubmit={this.handleSearch} style={{width: 400, padding: '2px 4px', display: "spac"}} >
              <InputBase
                style={{marginLeft: '10px', width: 325}}
                placeholder="Search Greengrocers/Supermarket or items"
                value={this.state.searchText}
                onChange={event => this.handleChange({searchText: event.target.value})}
              />
              <IconButton type="submit" aria-label="search">
                <SearchIcon />
              </IconButton>
            </Paper>
          </Grid>
          <Grid item xs={12}>
            <div className="cardbody">
              <Grid container justify="space-evenly" spacing={2}>
                {this.state.greengrocer && this.state.greengrocer.length !== 0 ? this.state.greengrocer.map(greengrocer => (
                  <Grid item xs={5} key={greengrocer.id}>
                    <GreengrocerCard userId={this.props.currentUser.id} greengrocerId={greengrocer.id} greengrocerInfo={greengrocer.information} />
                  </Grid>
                )) : <Typography variant="h5"><i>No result matches your search, please try again...</i></Typography>}
              </Grid>
            </div>
          </Grid>
        </Grid>
      </div>
    ) : <div />;
  }
}

export default CustomerHome;