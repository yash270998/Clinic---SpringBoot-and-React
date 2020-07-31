import React from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class CollectClinicals extends React.Component{
    state = {}
    
    componentWillMount(){
        axios.get('http://localhost:8080/clinicalservices/api/patients/'+this.props.match.params.patientId)
        .then(res=>{
            this.setState(res.data)
        })
    }

    handleSubmit(event){
        event.preventDefault();
        const data = {
            id:this.props.match.params.patientId,
            componentName:this.componentName,
            componentValue:this.componentValue
        }
        console.log(data);
        axios.post('http://localhost:8080/clinicalservices/api/clinicals/',data)
        .then(res=>{
            toast("Patient Data Successfully!",{autoClose:3000,position:toast.POSITION.BOTTOM_CENTER})
        })
    }
    render(){
        return(<div>
            <h2>Patient Details</h2>
            First Name: {this.state.firstName}
            Last Name: {this.state.lastName}
            Age : {this.state.age}
            <br/>
            <h2>Clinical Data</h2>
            <form>
                Clinical Entry type:<br/>
                <select onChange={(event)=>{this.componentName=event.target.value}}>
                    <option>Select One</option>
                    <option value="bp">Blood Pressure(Sys/Dys)</option>
                    <option value="hw">Height / Weight</option>
                    <option value="heartRate">Heart Rate</option>
                </select>
                Value: <input type='text' name='componentValue'onChange={(event)=>{this.componentValue=event.target.value}}/>
                <button onClick={this.handleSubmit.bind(this)}>Confirm</button>
            </form>
        </div>)
    }
}

export default CollectClinicals;