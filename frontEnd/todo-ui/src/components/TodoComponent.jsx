import { useEffect, useState } from "react"
import { createTodo, editTodo, getTodo } from "../service/TodoService";
import { useNavigate, useParams } from "react-router-dom";

const TodoComponent = () => {

  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [completed, setCompleted] = useState(false);
  const navigator = useNavigate();
  const {id} = useParams();


  useEffect(() => {
     if(id){
       getTodo(id).then(response => {
        setTitle(response.data.title);
        setDescription(response.data.description);
        setCompleted(response.data.completed);

       }).catch(error =>{
        console.log(error)
      })
     }
  }, [id])


  function saveOrUpdateTodo(e){
    e.preventDefault();
    const todo = {title, description, completed};

    if(id){
      editTodo(id, todo).then(() =>{
        navigator("/todos")
      }).catch(error =>{
        console.log(error)
      })
    }
    else{
      createTodo(todo).then(() =>{
        navigator("/todos")
      }).catch(error =>{
        console.log(error)
      })
    }
  
  }


  function pageTitle(){
    if(id){
      return <h2 className="text-center">Update Todo Form</h2>
    }else{
      return <h2 className="text-center">Add Todo Form</h2>
    }
  }





  return (
    <div className="container">
        <br /> <br />
       <div className="row">
         <div className="card col-md-6 offset-md-3 offset-md-3">
           {
            pageTitle()
           }
           <div className="card-bod">
              <form>

                  <div className="form-group mb-2">
                    <label className="form-label">Title:</label>
                    <input className="form-control"
                    type="text" placeholder="Enter Title" 
                    name="title" value={title} onChange={(e) => setTitle(e.target.value)}
                    />
                  </div>  

                  <div className="form-group mb-2">
                    <label className="form-label">Description:</label>
                    <input className="form-control"
                    type="text" placeholder="Enter Description" 
                    name="description" value={description} onChange={(e) => setDescription(e.target.value)}
                    />
                  </div> 


                  <div className="form-group mb-2">
                    <label className="form-label">Completed:</label>
                    <select
                       className="form-control"
                       value={completed}
                       onChange={(e) => setCompleted(e.target.value)}
                    >
                        <option value={false}>No</option>
                        <option value={true}>Yes</option>

                    </select>
                  </div> 

                 
                  <button className="btn btn-success" onClick={saveOrUpdateTodo}>Submit</button>
                  

              </form>
           </div>
         </div>
       </div>
    </div>
  )
}

export default TodoComponent