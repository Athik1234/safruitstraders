import React from 'react';
import {
  Typography,
  Card,
  CardHeader,
  IconButton,
  CardActions,
  Grid
      } from "@material-ui/core";
import AddIcon from '@material-ui/icons/Add';
import RemoveIcon from '@material-ui/icons/Remove';
import DeleteIcon from '@material-ui/icons/Delete';
import "./Card.css";
const axios = require('axios').default;

class ItemCard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      number : 0
    }
    this.handleAdd = this.handleAdd.bind(this);
    this.handleMinus = this.handleMinus.bind(this);
    this.removeItem = this.removeItem.bind(this);
  }

  handleAdd() {
    this.setState({number: this.state.number + 1});
    this.props.addItem("add", this.props.item);
  }

  handleMinus() {
    this.setState({number: this.state.number - 1});
    this.props.addItem("minus", this.props.item);
  }

  removeItem() {
    axios.post("/api/greengrocer/removeItem", {
      greengrocerId : this.props.currentUser.id,
      item : this.props.item
    }).then(
      response => {
        this.props.getAllitems();
      }
    ).catch(err => console.log(err));
  }

  render() {
    return this.props.item && this.props.currentUser ? (
      <Card>
        <CardHeader
          style={{backgroundColor: "#FAFAD2", height: "30px"}}
          titleTypographyProps={{variant:'body1'}}
          title={this.props.item.itemName}
          subheader={"₹ " + this.props.item.price}
        />
        <img className="itemCardImage" src= {this.props.item.imageUrl} alt={this.props.item.itemName} />
        <CardActions style={{backgroundColor: "#e6f7ff"}}>
          {this.props.currentUser.type !== "greengrocer" ?
          <Grid container justify="center" alignItems="center">
            <IconButton disabled={this.state.number === 0} onClick={this.handleMinus}>
              <RemoveIcon />
            </IconButton>
            <Typography variant="h5">{this.state.number}</Typography>
            <IconButton onClick={this.handleAdd}>
              <AddIcon />
            </IconButton>
          </Grid> : 
          <Grid container justify="center" alignItems="center">
            <IconButton onClick={this.removeItem}>
              <DeleteIcon />
            </IconButton>
          </Grid>
          }
        </CardActions>
      </Card>
    ) : <div />;
  }
}

export default ItemCard;