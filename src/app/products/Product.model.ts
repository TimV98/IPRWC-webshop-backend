export class Product {

  public productID: number;
  public name: string;
  public imageURL: string;
  public brand: string;
  public size: number;
  public price: number;
  public description: string;


  constructor(productNumber: number, productName: string, imageURL: string, brand: string, colour: string, size: number, price: number, description: string) {
    this.productID = productNumber;
    this.name = productName;
    this.imageURL = imageURL;
    this.brand = brand;
    this.size = size;
    this.price = price;
    this.description = description;
  }
}
