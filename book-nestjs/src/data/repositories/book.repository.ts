import { Inject, Injectable } from '@nestjs/common';
import { Book } from '../../domain/model/book';
import { Model } from 'mongoose';

@Injectable()
export class BookRepository {
  constructor(
    @Inject('BOOK_MODEL')
    private readonly bookModel: Model<Book>,
  ) {
  }

  async findAllByIsbn(isbn: string): Promise<Book[]> {
    return await this.bookModel.find({ isbn });
  }
}
