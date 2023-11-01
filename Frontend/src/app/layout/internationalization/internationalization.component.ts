import { Component, OnInit, ViewChild } from '@angular/core';
import { OverlayPanel, OverlayPanelModule } from 'primeng/overlaypanel';
import { ButtonModule } from 'primeng/button';
import { TranslocoService } from '@ngneat/transloco';

import { TranslocoModule } from '@ngneat/transloco';

interface Language {
  name: string;
  code: string;
}

@Component({
  selector: 'app-internationalization',
  templateUrl: './internationalization.component.html',
  styleUrls: ['./internationalization.component.css']
})
export class InternationalizationComponent implements OnInit {
  Languages: Language[] | undefined;
  @ViewChild('op') overlayPanel: OverlayPanel;

  selectedLanguage: Language | undefined;
  constructor(private translateService: TranslocoService) { }
  ngOnInit() {
    this.Languages = [
      { name: 'English', code: 'en' },
      { name: 'French', code: 'fr' }
    ];
  }
  onLanguageChange(event: any) {
    // Handle the change event here
    this.update(this.selectedLanguage);
    this.closeOverlayPanel();


  }

  public update(lang: any) {

    const currentLang = this.translateService.getActiveLang();

    const newLang = currentLang === 'fr' ? 'en' : 'fr';

    this.translateService.setActiveLang(newLang);

  }
  closeOverlayPanel() {
    this.overlayPanel.hide();
  }

}
