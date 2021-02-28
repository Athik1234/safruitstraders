import React from 'react';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Radio from '@material-ui/core/Radio';
const axios = require('axios').default;

class GreengrocerInfo extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      information : undefined,
      status : "close",
      name: "",
      description : "",
      imageUrl : "",
      tag1 : "",
      tag2 : "",
      tag3 : "",
      alert : ""
    }
    this.getInformation = this.getInformation.bind(this);
    this.updateInformation = this.updateInformation.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    this.getInformation();
  }

  getInformation() {
    let greengrocerId = this.props.currentUser.id;
    axios.get("/api/greengrocer/information/" + greengrocerId).then(
      response => {
        this.setState({
          information : response.data,
          status : response.data.open ? "open" : "close",
          name : response.data.greengrocerName ? response.data.greengrocerName : "",
          description : response.data.description ? response.data.description : "",
          imageUrl : response.data.imageUrl ? response.data.imageUrl : "",
          tag1 : response.data.tag1 ? response.data.tag1 : "",
          tag2 : response.data.tag2 ? response.data.tag2 : "",
          tag3 : response.data.tag3 ? response.data.tag3 : "",
        });
      }
    ).catch(err => console.log(err));
  }

  updateInformation(event) {
    event.preventDefault();
    axios.post("/api/greengrocer/information/", {
      greengrocerId : this.props.currentUser.id,
      status : this.state.status === "open" ? true : false,
      name : this.state.name,
      description : this.state.description,
      imageUrl : this.state.imageUrl,
      tag1 : this.state.tag1,
      tag2 : this.state.tag2,
      tag3 : this.state.tag3,
    }).then(response => {
      this.setState({alert : "The greengrocer information has been updated"})
    }).catch(err => console.log(err));
  }

  handleChange(content) {
    this.setState(content);
  }

  render() {
    return this.state.information ? (
      <Grid container justify="center">
        <Grid item xs={5}>
          <div className="container">
            <ShoppingCartIcon className="icon"/>
            <Typography component="h1" variant="h5">
              Provide Your Greengrocer Detail
            </Typography>
            <Typography variant="body1" color="error">
              {this.state.alert}
            </Typography>
            <form onSubmit={this.updateInformation}>
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                label="Greengrocer Name"
                type="text"
                value={this.state.name}
                autoFocus
                onChange={event => this.handleChange({name: event.target.value})}
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                label="Description"
                type="text"
                value={this.state.description}
                onChange={event => this.handleChange({description: event.target.value})}
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                label="Image URL"
                type="text"
                value={this.state.imageUrl}
                onChange={event => this.handleChange({imageUrl: event.target.value})}
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                label="tag1"
                type="text"
                value={this.state.tag1}
                onChange={event => this.handleChange({tag1: event.target.value})}
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                label="tag2"
                type="text"
                value={this.state.tag2}
                onChange={event => this.handleChange({tag2: event.target.value})}
              />
              <TextField
                variant="outlined"
                margin="normal"
                required
                fullWidth
                label="tag3"
                type="text"
                value={this.state.tag3}
                onChange={event => this.handleChange({tag3: event.target.value})}
              />
              <FormControl component="fieldset">
                <FormLabel component="legend">Choose Greengrocer Status </FormLabel>
                <RadioGroup row aria-label="status" name="status" value={this.state.status} onChange={event => this.handleChange({status: event.target.value})}>
                  <FormControlLabel value="open" control={<Radio />} label="Open" />
                  <FormControlLabel value="close" control={<Radio />} label="Close" />
                </RadioGroup>
              </FormControl>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
              >
                Update
              </Button>
            </form>
          </div>
        </Grid>
      </Grid>
    ) : <div />;
  }
}

export default GreengrocerInfo;