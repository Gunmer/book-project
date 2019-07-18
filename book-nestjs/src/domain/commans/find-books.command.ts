import { Injectable } from '@nestjs/common';
import { BookRepository } from '../../data/repositories/book.repository';
import { Book } from '../model/book';

@Injectable()
export class FindBooksCommand {
  constructor(
    private readonly bookRepository: BookRepository,
  ) {
  }

  async execute(isbn: string): Promise<Book[]> {
    return await this.bookRepository.findAllByIsbn(isbn);
  }

}
