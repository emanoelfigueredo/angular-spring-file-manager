export class Util {

  public static obterPercentualScrollDaPagina(): number | void {
    let doc = document.documentElement;
    window.addEventListener('scroll', () => {
      let value1 = (100 * doc.scrollTop);
      let value2 = (doc.scrollHeight - doc.clientHeight);
      let value = value1 / value2;
      return value;
    })
  }

}
