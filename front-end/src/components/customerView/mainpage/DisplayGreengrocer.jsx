import React from 'react';
import {
  Grid, Typography, Button
} from '@material-ui/core';
import { Link } from "react-router-dom";
import "./Customer.css";
import ItemCard from "../../card/ItemCard";
const axios = require('axios').default;

class DisplayGreengrocer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      greengrocerId: this.props.match.params.greengrocerId,
      greengrocer: undefined,
      subtotal: 0,
      shopcart: []
    }
    this.getGreengrocer = this.getGreengrocer.bind(this);
    this.addItem = this.addItem.bind(this);
    this.addToCart = this.addToCart.bind(this);
  }

  componentDidMount() {
    this.getGreengrocer();
  }

  getGreengrocer() {
    axios.get("/api/greengrocer/" + this.state.greengrocerId).then(
      response => {
        this.setState({greengrocer: response.data});
      }
    ).catch(err => console.log(err));
  }

  addItem(action, item) {
    if (action === "add") {
      let tempArray = this.state.shopcart;
      tempArray.push(item);
      this.setState({subtotal: this.state.subtotal + item.price, shopcart: tempArray});
    } else if (action === "minus") {
      let tempArray2 = this.state.shopcart.filter(preItem => preItem.itemName !== item.itemName);
      this.setState({subtotal: this.state.subtotal - item.price, shopcart: tempArray2});
    }
  }

  addToCart() {
    axios.post("/api/order/addToCart", {
      customerId: this.props.currentUser.id,
      greengrocerId: this.state.greengrocerId,
      shopcart: this.state.shopcart
    }).then(response => console.log(response.data)).catch(err => console.log(err));
  }

  render() {
    return this.props.currentUser && this.state.greengrocer ? (
      <div>
        <Typography variant="h4">
        <img className="littleImage" src= {this.state.greengrocer.information.imageUrl} alt={this.state.greengrocer.information.greengrocerName} />
        <i><b>Welcome to {this.state.greengrocer.information.greengrocerName}</b></i>
        </Typography>
        {!this.state.greengrocer.information.open ? (
          <Typography variant="body1" color="error">Closed, will go back soon...</Typography>
        ) : null}
        <br />
        <Typography variant="body1" color="textSecondary" component="p">
          <i>{this.state.greengrocer.information.description}</i>
        </Typography>
        <br />
        <br />
        <br />
        <Grid container spacing={3} justify="space-evenly">
          {this.state.greengrocer.list.map((item, index) => (
            <Grid item xs={3} key={index}>
              <ItemCard item={item} addItem={this.addItem} currentUser={this.props.currentUser} />
            </Grid>
          ))}
        </Grid>
        <div className="checkoutBox">
          <Grid container justify="flex-end">
              <Grid item>
                <Typography variant="h5"><i>Subtotal : ₹ {this.state.subtotal}</i></Typography>
              </Grid>
          </Grid>
          <br />
          <Grid container justify="flex-end">
              <Grid item>
                <Link to={"/customer/home"} style={{textDecoration: "none"}}>
                  <Button variant="outlined" color="primary" size="large" disabled={!this.state.greengrocer.information.open || this.state.subtotal === 0} onClick={this.addToCart}>
                    Add to Cart
                  </Button>
                </Link>
              </Grid>
          </Grid>
        </div>
      </div>
    ) : <div>This greengrocer is not available</div>
  }
}

export default DisplayGreengrocer;