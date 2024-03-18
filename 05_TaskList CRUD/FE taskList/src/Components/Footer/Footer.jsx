import './Footer.css';

function Footer() {
  return (
    <footer>
      <p className='footer-text'>&copy; {new Date().getFullYear()} All right reserved.</p>
    </footer>
  );
}

export default Footer;
