import { Controller, Get, Query } from '@nestjs/common';
import { FindBooksCommand } from '../../domain/commans/find-books.command';

@Controller('book')
export class BookController {

  constructor(
    private readonly findBooksCommand: FindBooksCommand,
  ) {
  }

  @Get()
  getBooks(@Query('isbn')isbn: string) {
    return this.findBooksCommand.execute(isbn);
  }

}
