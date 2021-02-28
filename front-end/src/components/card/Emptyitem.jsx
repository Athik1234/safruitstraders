import React from 'react';
import {
  Card,
  CardHeader,
  IconButton,
  CardContent,
  TextField, Divider
      } from "@material-ui/core";
import AddIcon from '@material-ui/icons/Add';
import "./Card.css";
const axios = require('axios').default;

class Emptyitem extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      itemName: "",
      imageUrl: "",
      price : 0
    }
    this.addTolist = this.addTolist.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  addTolist(event) {
    event.preventDefault();
    axios.post("/api/greengrocer/addTolist", {
      greengrocerId : this.props.currentUser.id,
      itemName : this.state.itemName,
      imageUrl : this.state.imageUrl,
      price : this.state.price
    }).then(
      response => {
        this.props.getAllitems();
        this.setState({itemName : "", imageUrl : "", price : 0})
      }
    ).catch(err => console.log(err));
  }

  handleChange(content) {
    this.setState(content);
  }

  render() {
    return this.props.currentUser ? (
      <Card>
        <CardHeader
          style={{backgroundColor: "#FAFAD2", height: "auto"}}
          titleTypographyProps={{variant:'body1'}}
          title={<i>Please provide the Item detail</i>}
          subheader="Press {+} and you will see how your Item looks like"
        />
        <CardContent>
          <form onSubmit={this.addTolist}>
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              label="Item Name"
              type="text"
              value={this.state.itemName}
              autoFocus
              onChange={event => this.handleChange({itemName: event.target.value})}
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              label="Item Image URL"
              type="text"
              value={this.state.imageUrl}
              onChange={event => this.handleChange({imageUrl: event.target.value})}
            />
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              label="₹ Item Price/Kg"
              type="number"
              value={this.state.price}
              onChange={event => this.handleChange({price: event.target.value})}
            />
            <Divider />
            <IconButton type="submit">
              <AddIcon />
            </IconButton>
          </form>      
        </CardContent>
      </Card>
    ) : <div />;
  }
}

export default Emptyitem;