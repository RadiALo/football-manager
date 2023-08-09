import { Component } from '@angular/core';

import { TeamRequest, TeamResponse } from '../model/team';
import { TeamService } from '../team.service';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent {
  teams: TeamResponse[] = [];
  newTeam: TeamRequest = {} as TeamRequest;
  page: number = 0;
  size: number = 5;

  constructor(private teamService: TeamService) { }

  ngOnInit() {
    this.getTeams(this.page, this.size);
  }
}
