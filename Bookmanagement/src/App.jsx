import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import { Route, Routes } from 'react-router-dom'
import Books from './comp/Books'
import Student from './comp/Student'
import Issue from './comp/Issue'
import Nav from './comp/Nav'
import Borrower from './comp/Borrower'
import User from './comp/User'
import Booklist from './comp/Booklist'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <Nav/>
      <Routes>
        <Route path='/list' element={<Booklist/>}/>
        <Route path='/' element={<User/>}/>
        <Route path='/stud' element={<Student/>}/>
        <Route path='/book' element={<Books/>}/>
        <Route path='/issue' element={<Issue/>}/>
        <Route path='/borrow' element={<Borrower/>}/>
      </Routes>
    </>
  )
}

export default App
