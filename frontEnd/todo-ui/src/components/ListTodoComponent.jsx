import { useEffect, useState } from "react"
import { completeTodo, deleteTodo, inCompleteTodo, listTodos } from "../service/TodoService";
import { useNavigate } from "react-router-dom";


const ListTodoComponent = () => {

  const [todos, setTodos] = useState([]);

  const navigator = useNavigate();


  useEffect(() =>{
    listOfTodos();
  },[])


  function listOfTodos(){
    listTodos().then(response =>{
      setTodos(response.data)
    }).catch(error =>{
      console.log(error)
    })
  }


  function addNewTodo(){
     navigator("/add-todo")
  }


  function updateTodo(id){
     navigator(`/edit-todo/${id}`)
  }

  function removeTodo(id){
    deleteTodo(id).then(() =>{
      listOfTodos();
    }).catch(error =>{
      console.log(error)
    })
  }

  function markCompleteTodo(id){
     completeTodo(id).then(() =>{
        listOfTodos()
     }).catch(error =>{
      console.log(error)
    })
  }

  function markInCompleteTodo(id){
    inCompleteTodo(id).then(() =>{
      listOfTodos()
    }).catch(error =>{
      console.log(error)
    })
  }


  return (
    <div className="container">
        <h1 className="text-center">Todo List</h1>

        <button className="btn btn-primary" onClick={addNewTodo}>Add New Todo</button>

        <table className="table table-striped">
           <thead>
              <tr>
                <th>Title</th>
                <th>Description</th>
                <th>completed</th>
                <th>Actions</th>
              </tr>
           </thead>
           <tbody>
                {
                  todos.map(temp =>
                     <tr key={temp.id}>
                       <td>{temp.title}</td>
                       <td>{temp.description}</td>
                       <td>{temp.completed ? 'YES' : 'NO'}</td>
                       <td>
                         <button className="btn btn-info" onClick={() => updateTodo(temp.id)}>Update</button>
                         <button className="btn btn-danger" onClick={() => removeTodo(temp.id)} style={ {marginLeft:'10px'} }>Delete</button>
                         <button className="btn btn-success" onClick={() => markCompleteTodo(temp.id)}  style={ {marginLeft:'10px'} }>complete</button>
                         <button className="btn btn-warning" onClick={() => markInCompleteTodo(temp.id)} style={ {marginLeft:'10px'} }>In complete</button>
                       </td>
                     </tr>
                  )
                }
           </tbody>
        </table>
    </div>
  )
}

export default ListTodoComponent